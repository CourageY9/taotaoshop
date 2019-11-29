package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/11/29:14:03  <br/>
 * Description:Item  Controller层
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * <pre>
     * Description :  根据商品ID获取商品信息  <br/>
     * ChangeLog : 1. 创建 (2019/11/29 14:19 [yangyi]);
      * @param itemId  商品ID
      * @return com.taotao.pojo.TbItem
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/item/{itemId}")
    public TbItem getItem(@PathVariable long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
}
