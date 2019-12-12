package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/12:17:26  <br/>
 * Description:Item-web Controller层
 */
@Controller
@RequestMapping("/item/")
public class ItemController {

    @Autowired
    private ItemService itemService;


    /**
     * <pre>
     * Description :  展示商品描述信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/12 17:41 [yangyi]);
      * @param itemId
     * @param model
      * @return java.lang.String
     * </pre>
     */
    @RequestMapping("{itemId}")
    public String getItem(@PathVariable long itemId, Model model){
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * <pre>
     * Description :  根据商品ID查询商品描述信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/12 17:46 [yangyi]);
      * @param itemId
     * @param model
      * @return java.lang.String
     * </pre>
     */
    @ResponseBody
    @RequestMapping("desc/{itemId}")
    public String getItemDesc(@PathVariable long itemId,Model model){
        TbItemDesc itemDesc = itemService.findItemdescById(itemId);
        return itemDesc.getItemDesc();
    }
}
