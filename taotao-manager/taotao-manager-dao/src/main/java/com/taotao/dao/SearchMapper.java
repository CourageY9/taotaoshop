package com.taotao.dao;

import com.taotao.common.pojo.SearchItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:16:50  <br/>
 * Description:solr 格式下的商品
 */
public interface SearchMapper {

    @Select("select a.id,a.title,a.sellPoint,a.price,a.image,b.`name` categoryName,c.itemDesc from tbitem a inner join tbitemcat b on a.cid=b.id inner join tbitemdesc c on a.id=c.itemId where a.status=1")
    public List<SearchItem> getAllItem();

    @Select("select a.id,a.title,a.sellPoint,a.price,a.image,b.`name` categoryName,c.itemDesc from tbitem a inner join tbitemcat b on a.cid=b.id inner join tbitemdesc c on a.id=c.itemId where a.status=1 and a.id=#{id}")
    public List<SearchItem> getItem(long id);
}
