package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

    public TbItem getItemById(long itemId); //根据ID获取商品信息

    public EasyUIResult getItemList(int page,int rows);  //获取所有商品信息

    public TaotaoResult deleteItems(Integer[] ids); //批量删除商品
}
