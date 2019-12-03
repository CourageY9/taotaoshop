package com.taotao.dao;


import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentCategoryMapper {

    @Select("select * from tbcontentcategory where parentId=#{parentId}")
    public List<TbContentCategory> getContentCategory(long parentId);   //根据parentId查询内容分类信息
}