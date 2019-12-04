package com.taotao.dao;

import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentMapper {

    @Select("select * from tbcontent where categoryId=#{categoryId};")
    public List<TbContent> getContentByCategoryId(long categoryId);    //通过categoryId查询内容信息

    @Insert("insert into tbcontent( categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated) VALUE (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    public int addContent(TbContent tbContent);     //添加contente对象

    @Delete("<script> delete from tbcontent where id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    public int deleteContent(long[] ids);   //删除content对象
}