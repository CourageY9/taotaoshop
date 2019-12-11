package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/11:11:51  <br/>
 * Description:solr Dao类
 */
//将该类作为dao层注入spring
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult selectItem(SolrQuery query) throws Exception{
        //根据query执行查询solr索引库
        QueryResponse response = solrServer.query(query);
        //获取Solr 商品列
        SolrDocumentList documentList = response.getResults();
        //商品列表
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : documentList) {
            SearchItem item = new SearchItem();
            item.setId(Long.valueOf(String.valueOf(solrDocument.get("id"))));
            item.setCategoryName(solrDocument.get("item_category_name")+"");
            item.setImage(solrDocument.get("item_image")+"");
            item.setPrice(Long.parseLong(String.valueOf(solrDocument.get("item_price"))));
            item.setSellPoint(String.valueOf(solrDocument.get("item_sell_point")));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            //有高亮显示的内容时。
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            //添加到商品列表
            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        //商品列表
        result.setItemList(itemList);
        //总记录数
        result.setRecordCount(documentList.getNumFound());
        return result;

    }
}
