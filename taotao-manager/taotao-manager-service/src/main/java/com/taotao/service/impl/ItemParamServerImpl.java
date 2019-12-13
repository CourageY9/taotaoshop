package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JedisUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.dao.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/13:11:28  <br/>
 * Description:商品规格参数模板Server层
 */
@Service
public class ItemParamServerImpl implements ItemParamServer {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Autowired
    private TbItemParamItemMapper itemMapper;   //商品规格参数详细表Dao

    @Value("${ITEM_INFO}")
    private String ITEM_INFO;   //商品信息描述

    @Value("${ITEM_OUT_TIME}")
    private Integer ITEM_OUT_TIME;  //商品过期时间（1天）

    /**
     * <pre>
     * Description :  根据itemCatId 查询商品模板信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 11:29 [yangyi]);
      * @param itemCatId
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult getItemParamByItemCatId(long itemCatId) {
        TbItemParam tbItemParam = tbItemParamMapper.getParamByCatId(itemCatId);
        if (tbItemParam != null){
            return TaotaoResult.ok(tbItemParam);
        }
        return TaotaoResult.ok();
    }

    /**
     * <pre>
     * Description :  添加商品规格参数模板  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 14:10 [yangyi]);
      * @param tbItemParam
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult addItemParam(TbItemParam tbItemParam) {
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        int i = tbItemParamMapper.addItemParam(tbItemParam);
        if (i == 1){
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "添加失败");
    }

    /**
     * <pre>
     * Description :  查询所有的已设置的商品规格模板  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 15:35 [yangyi]);
      *
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    public EasyUIResult showAll() {
        List<TbItemParam> list = tbItemParamMapper.getAllItemParam();
        EasyUIResult result = new EasyUIResult();
        if (list.size() > 0){
            result.setRows(list);
            result.setTotal(list.size());
        }
        return result;
    }


    public String getItemPatamItemById(long itemId) {
        String redisKey = ITEM_INFO+":"+itemId+":Param";
        //缓存中取数据
        String s = JedisUtils.get(redisKey);
        if (StringUtils.isNotBlank(s)){
            JedisUtils.expire(redisKey, ITEM_OUT_TIME);
            return s;
        }
        TbItemParamItem itemParamItem = itemMapper.getItemParamById(itemId);
        String paramData = itemParamItem.getParamData();
        //生成html        因为没有web页面。。。。所以自己拼
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        //加入缓存
        JedisUtils.set(redisKey, sb.toString());
        JedisUtils.expire(redisKey, ITEM_OUT_TIME); //
        return sb.toString();

    }
}
