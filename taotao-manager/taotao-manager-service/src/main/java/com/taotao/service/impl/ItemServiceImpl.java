package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * <pre>
     * Description :  获取商品ID  <br/>
     * ChangeLog : 1. 创建 (2019/11/29 10:14 [yangyi]);
      * @param itemId
      * @return com.taotao.pojo.TbItem
     * </pre>
     */
    public TbItem getItemById(long itemId) {
        return tbItemMapper.findItemById(itemId);
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
}
