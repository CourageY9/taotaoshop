package com.taotao.dao;

import java.util.List;

import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;

public interface TbItemCatMapper {

    @Select("select * from tbitemcat where parentId=#{id}")
    public List<TbItemCat> getItemCat(long id);
}