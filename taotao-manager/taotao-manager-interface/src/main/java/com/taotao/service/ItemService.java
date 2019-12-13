package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {

    public TbItem getItemById(long itemId); //根据ID获取商品信息

    public EasyUIResult getItemList(int page,int rows);  //获取所有商品信息

    public TaotaoResult deleteItems(long[] ids); //批量删除商品

    public TaotaoResult addItem(TbItem item,String desc,String itemParams);   //添加商品

    public TbItemDesc findItemdescById(long itemId);    //根据itemId查询商品描述信息
}
