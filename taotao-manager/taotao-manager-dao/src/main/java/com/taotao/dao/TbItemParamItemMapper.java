package com.taotao.dao;


import com.taotao.pojo.TbItemParamItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemParamItemMapper {

    @Insert("insert into tbitemparamitem(itemId, paramData, created, updated) value (#{itemId},#{paramData},#{created},#{updated})")
    public int addItemParamItem(TbItemParamItem tbItemParamItem);   //添加商品规格参数详细表

    @Select("select * from tbitemparamitem where itemId=#{itemId};")
    public TbItemParamItem getItemParamById(long itemId);   //根据商品ID查询商品规格参数详情
}