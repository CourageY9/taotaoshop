package com.taotao.dao;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Select;

public interface TbItemMapper {

    @Select("select * from tbitem where id=#{id}")
    public TbItem findItemById(long itemId);
}