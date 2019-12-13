package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/13:11:22  <br/>
 * Description:商品规格参数模板
 */
public interface ItemParamServer {

    public TaotaoResult getItemParamByItemCatId(long itemCatId);    //根据tbitemparam表中的itemCatId查询规格模板

    public TaotaoResult addItemParam(TbItemParam tbItemParam);  //添加商品规格模板

    public EasyUIResult showAll();  //查询所有商品规格参数列表

    public String getItemPatamItemById(long itemId);    //根据商品ID查该商品规格参数的详细信息
}
