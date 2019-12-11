package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:17:06  <br/>
 * Description:
 */
@Controller
public class SearchController {

    @Autowired
    private SolrItemService solrItemService;

    /**
     * <pre>
     * Description :  将查询到的商品信息添加到solr域中  <br/>
     * ChangeLog : 1. 创建 (2019/12/10 17:08 [yangyi]);
      *
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/index/importall")
    public TaotaoResult addItemToSolr(){
        try {
            TaotaoResult result = solrItemService.addAllItem();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(500, "系统错误");
    }
}
