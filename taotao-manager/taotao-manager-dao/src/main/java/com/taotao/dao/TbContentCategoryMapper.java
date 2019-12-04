package com.taotao.dao;


import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbContentCategoryMapper {

    @Select("select * from tbcontentcategory where parentId=#{parentId}")
    public List<TbContentCategory> getContentCategory(long parentId);   //根据parentId查询内容分类信息

    @Insert("insert into tbcontentcategory( parentId, name, status, sortOrder, isParent, created, updated) VALUE (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    public int addContentCategory(TbContentCategory tbContentCategory); //添加内容分类管理信息（节点信息）

    @Select("select * from tbcontentcategory where id=#{parentId}")
    public TbContentCategory findContentCategoryById(long parentId);    //通过查询节点信息id为parentI的内容

    @Update("update tbcontentcategory set isParent=#{isParent} where id=#{id}")
    public void updateTrue(TbContentCategory tbContentCategory);   //修改是否为父节点信息

    @Update("update tbcontentcategory set name=#{name},updated=#{updated} where id=#{id}")
    public int updateName(TbContentCategory tbContentCategory);    //重命名

    @Delete("delete from tbcontentcategory where id=#{id}")
    public int deleteContentCategoryById(long id);  //删除节点信息
}