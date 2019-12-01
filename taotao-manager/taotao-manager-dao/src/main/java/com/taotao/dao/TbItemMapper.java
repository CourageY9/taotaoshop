package com.taotao.dao;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper {

    @Select("select * from tbitem where id=#{id}")
    public TbItem findItemById(long itemId);    //根据商品ID获取商品信息

    @Select("select * from tbitem")
    public List<TbItem> findAllItem();  //获取所有商品信息
}