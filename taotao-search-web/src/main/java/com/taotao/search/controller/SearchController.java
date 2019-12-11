package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/11:14:44  <br/>
 * Description:
 */
@Controller
public class SearchController {

    @Autowired
    private SolrItemService solrItemService;

    @Value("${ITEM_ROWS}")
    private int ITEM_ROWS;  //默认一页的个数

    @RequestMapping("/search")
    public String selectItem(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") int page, Model model){
        try {
            //解决前端传来的乱码
            byte[] bytes = queryString.getBytes("ISO-8859-1");
            String queryS = new String(bytes,"UTF-8");
            SearchResult result = solrItemService.selectItemToSolr(queryS, page, ITEM_ROWS);
            model.addAttribute("query", queryS);
            model.addAttribute("totalPages", result.getPageCount());
            model.addAttribute("itemList", result.getItemList());
            model.addAttribute("page", page);
            return "search";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "search";
    }
}
