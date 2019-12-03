package com.taotao.dao;

import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;

public interface TbItemDescMapper {

    @Insert("insert into tbitemdesc(itemId, itemDesc, created, updated) value (#{itemId},#{itemDesc},#{created},#{updated})")
    public int addItemDesc(TbItemDesc itemDesc);
}