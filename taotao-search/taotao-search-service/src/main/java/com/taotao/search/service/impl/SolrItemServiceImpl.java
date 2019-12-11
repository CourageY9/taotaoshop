package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.SearchMapper;
import com.taotao.common.pojo.SearchItem;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SolrItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:16:49  <br/>
 * Description:
 */
@Service
public class SolrItemServiceImpl implements SolrItemService {

    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchDao searchDao;

    /**
     * <pre>
     * Description :  从数据库中查询数据添加到solr中  <br/>
     * ChangeLog : 1. 创建 (2019/12/11 11:58 [yangyi]);
      *
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult addAllItem() {
        try {
            List<SearchItem> list = searchMapper.getAllItem();
            for (SearchItem item : list){
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", item.getId());  //商品ID
                document.addField("item_title", item.getTitle());   //商品标题
                document.addField("item_sell_point", item.getSellPoint());  //商品卖点
                document.addField("item_price", item.getPrice());   //商品价格
                document.addField("item_image", item.getImage());   //商品图片
                document.addField("item_category_name", item.getCategoryName());    //商品分类
                document.addField("item_desc", item.getItemDesc()); //商品描述
                solrServer.add(document);
            }
            solrServer.commit();
            return TaotaoResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(500, "获取数据失败");
    }

    /**
     * <pre>
     * Description :  TODO  <br/>
     * ChangeLog : 1. 创建 (2019/12/11 12:00 [yangyi]);
      * @param queryString 关键字
     * @param page  当前页
     * @param rows  一页多少行
      * @return com.taotao.common.pojo.SearchResult
     * </pre>
     */
    public SearchResult selectItemToSolr(String queryString, int page, int rows) {
        SearchResult result = new SearchResult();
        try {
            SolrQuery query = new SolrQuery();
            if (queryString != null && !"".equals(queryString)){
                query.setQuery(queryString);
            }else {
                query.setQuery("*:*");
            }
            query.set("df", "item_keywords");   //设置solr默认域
            query.setStart((page-1)*rows);  //设置页数
            query.setRows(rows);            //设置一页多少条
            query.setHighlight(true);       //开启高亮
            query.addHighlightField("item_title");      //需要高亮的域
            query.setHighlightSimplePre("<span style='color:red'>");    //前
            query.setHighlightSimplePost("</span>");    //后
            SearchResult record = searchDao.selectItem(query);
            long totalNum = record.getPageCount();   //总条数

            //最终返回结果集

            result.setItemList(record.getItemList());   //返回结果
            result.setRecordCount(totalNum);     //总条数
            long pageNum = totalNum%rows==0?totalNum/rows:totalNum/rows+1;
            result.setPageCount(pageNum);   //总页数
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        List<SearchItem> list = new ArrayList<>();
        result.setItemList(list);
        result.setPageCount(0);
        result.setRecordCount(0);
        return null;
    }
}
