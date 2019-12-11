package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class solrDemoTest {

    @Test
    public void show() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.58.106:8080/solr");
        SolrQuery query = new SolrQuery();
        query.setQuery("测试");
        query.set("df", "item_keywords");
        query.setHighlight(true);       //开启高亮
        query.addHighlightField("item_price");
        query.setHighlightSimplePre("<span style='color:red'>");  //前
        query.setHighlightSimplePost("</span>");  //后
        QueryResponse response = solrServer.query(query);
        //执行查询获取放回结果集
        SolrDocumentList documentList = response.getResults();
        System.out.println("总条数："+documentList.getNumFound());
        for (SolrDocument doc : documentList){
            //获取高亮显示
            Map<String,Map<String, List<String>>> map = response.getHighlighting();
            //List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            List<String> list = map.get(doc.get("id")).get("item_price");
            String item_price = "";
            if (list != null && list.size()>0){
                item_price = list.get(0);
            }else {
                item_price = ""+ doc.get("item_price");
            }

            System.out.println(item_price);
        }

    }

}