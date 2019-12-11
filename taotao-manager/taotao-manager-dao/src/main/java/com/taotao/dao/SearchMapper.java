package com.taotao.dao;

import com.taotao.pojo.SearchItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:16:50  <br/>
 * Description:
 */
public interface SearchMapper {

    @Select("select a.id,a.title,a.sellPoint,a.price,a.image,b.`name` categoryName,c.itemDesc from tbitem a inner join tbitemcat b on a.cid=b.id inner join tbitemdesc c on a.id=c.itemId where a.status=1")
    public List<SearchItem> getAllItem();
}
