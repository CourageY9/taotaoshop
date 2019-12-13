package com.taotao.dao;


import com.taotao.pojo.TbItemParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemParamMapper {

    @Select("select * from tbitemparam where itemCatId=#{itemCatId}")
    public TbItemParam getParamByCatId(long itemCatId);   //根据itemCatId查询模板信息

    @Insert("insert into tbitemparam(itemCatId, paramData, created, updated) VALUE (#{itemCatId},#{paramData},#{created},#{updated})")
    public int addItemParam(TbItemParam tbItemParam);   //添加商品规程参数模板

    @Select("select * from tbitemparam where 1=1;")
    public List<TbItemParam> getAllItemParam(); //查询所有的商品规格参数模板
}