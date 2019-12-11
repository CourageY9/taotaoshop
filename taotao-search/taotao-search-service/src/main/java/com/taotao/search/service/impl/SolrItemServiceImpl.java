package com.taotao.search.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.SearchMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.search.service.SolrItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
