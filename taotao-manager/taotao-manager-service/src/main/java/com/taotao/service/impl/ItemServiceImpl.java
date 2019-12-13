package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JedisUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Auther: yangyi  <br/>
 * Date: 2019/11/29:9:41  <br/>
 * Description:Item Service层
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;    //商品规格参数详细表Dao

    @Autowired
    private JmsTemplate jmsTemplate;    //spring管理的ActiveMQ的连接

    @Autowired
    private Topic topicDestination;     //订阅与发布模式（发布者）

    @Value("${ITEM_INFO}")
    private String ITEM_INFO;   //商品信息描述

    @Value("${ITEM_OUT_TIME}")
    private Integer ITEM_OUT_TIME;  //商品过期时间（1天）

    /**
     * <pre>
     * Description :  获取商品ID  <br/>
     * ChangeLog : 1. 创建 (2019/11/29 10:14 [yangyi]);
      * @param itemId
      * @return com.taotao.pojo.TbItem
     * </pre>
     */
    public TbItem getItemById(long itemId) {
        try {
            String json = JedisUtils.get(ITEM_INFO+":"+itemId+":BASE");
            if (StringUtils.isNotBlank(json)){
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                JedisUtils.expire(ITEM_INFO+":"+itemId+":BASE",ITEM_OUT_TIME);  //重新设置过期时间
                return item;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem tbItem = tbItemMapper.findItemById(itemId);
        JedisUtils.set(ITEM_INFO+":"+itemId+":BASE", JsonUtils.objectToJson(tbItem));   //存redis缓存
        JedisUtils.expire(ITEM_INFO+":"+itemId+":BASE",ITEM_OUT_TIME);  //设置过期时间
        return tbItem;
    }

    /**
     * <pre>
     * Description :  获取所有的商品信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/1 18:40 [yangyi]);
      * @param page 当前页数
     * @param rows  当前页的条数（如pageSize）
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    public EasyUIResult getItemList(int page,int rows){
        //初始化分页信息 设置多少也，设置当前页多少条数据
        PageHelper.startPage(page, rows);

        List<TbItem> itemList = tbItemMapper.findAllItem(); //获取所有商品信息

        //获取分页后得商品信息
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(itemList);
        //设置返回结果集
        EasyUIResult result = new EasyUIResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(itemList);

        return result;
    }

    /**
     * <pre>
     * Description :  批量删除商品  <br/>
     * ChangeLog : 1. 创建 (2019/12/2 0:02 [yangyi]);
      * @param ids     商品ID
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult deleteItems(long[] ids){
        int result = tbItemMapper.deleteItems(ids);
        if (result != 0){
            return TaotaoResult.ok();
        }
        return null;
    }


    /**
     * <pre>
     * Description :  添加商品信息接口  <br/>
     * ChangeLog : 1. 创建 (2019/12/3 11:57 [yangyi]);
      * @param item 商品信息
     * @param desc  商品描述信息
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult addItem(TbItem item, String desc,String itemParams) {

        final long id = IDUtils.genItemId();  //自动生成ID
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);

        //添加商品信息表
        item.setId(id);
        item.setStatus((byte) 1);   //默认状态为1
        item.setCreated(date);  //创建时间
        item.setUpdated(date);  //修改时间
        int itemNum = tbItemMapper.addItem(item);

        //添加商品描述信息表
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);   //商品描述信息
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int itemDescNum = tbItemDescMapper.addItemDesc(tbItemDesc);

        //添加商品规格参数信息表
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(id);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        int itemParamNum = tbItemParamItemMapper.addItemParamItem(itemParamItem);

        if (itemNum==1 && itemDescNum==1 && itemParamNum==1){
            //ActiveMQ中发送商品id
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(String.valueOf(id));
                    return message;
                }
            });
            return TaotaoResult.ok();
        }

        return TaotaoResult.build(500, "添加商品失败，请重新添加");
    }


    /**
     * <pre>
     * Description :  根据商品id查询商品描述信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/12 17:37 [yangyi]);
      * @param itemId
      * @return com.taotao.pojo.TbItemDesc
     * </pre>
     */
    public TbItemDesc findItemdescById(long itemId) {
        String redisKey = ITEM_INFO+":"+itemId+":describe";
        try {
            String record = JedisUtils.get(redisKey);   //describe描述
            if (StringUtils.isNotBlank(record)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(record, TbItemDesc.class);
                JedisUtils.expire(redisKey,ITEM_OUT_TIME);
                return itemDesc;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemDesc itemDesc = tbItemDescMapper.fingTbitemdescbyId(itemId);
        JedisUtils.set(redisKey,JsonUtils.objectToJson(itemDesc));
        JedisUtils.expire(redisKey,ITEM_OUT_TIME);
        return itemDesc;
    }
}
