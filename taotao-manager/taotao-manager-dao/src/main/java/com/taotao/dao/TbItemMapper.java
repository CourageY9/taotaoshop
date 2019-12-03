package com.taotao.dao;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper {

    @Select("select * from tbitem where id=#{id}")
    public TbItem findItemById(long itemId);    //根据商品ID获取商品信息

    @Select("select * from tbitem")
    public List<TbItem> findAllItem();  //获取所有商品信息

    @Delete("<script> DELETE FROM tbitem WHERE id IN <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    public int deleteItems(long[] ids);  //删除商品

    @Insert("insert into tbitem(id, title, sellPoint, price, num, barcode, image, cid, status, created, updated) value (#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{status},#{created},#{updated})")
    public int addItem(TbItem item);    //添加商品
}