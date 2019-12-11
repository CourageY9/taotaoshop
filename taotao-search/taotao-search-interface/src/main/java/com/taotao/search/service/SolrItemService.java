package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:16:46  <br/>
 * Description:
 */
public interface SolrItemService {

    public TaotaoResult addAllItem();  //将数据中的数据导入solr

    public SearchResult selectItemToSolr(String queryString, int page, int rows);   //根据前端搜索条件查询（简单版）
}
