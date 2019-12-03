package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    /**
     * <pre>
     * Description :  获取所有商品信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/1 18:57 [yangyi]);
      * @param page
     * @param rows
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/item/list")
    public EasyUIResult getAllItem(int page,int rows){
        EasyUIResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * <pre>
     * Description :  批量删除商品  <br/>
     * ChangeLog : 1. 创建 (2019/12/2 0:04 [yangyi]);
      * @param ids
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/rest/item/delete")
    public TaotaoResult deleteItems(long[] ids){
        TaotaoResult result = itemService.deleteItems(ids);
        return result;
    }
    
    //测试
    @ResponseBody
    @RequestMapping("/rest/page/item-edit")
    public TaotaoResult findItemById(Integer id){
        System.out.println(id);
        return null;
    }

    /**
     * <pre>
     * Description :  添加商品接口  <br/>
     * ChangeLog : 1. 创建 (2019/12/3 14:49 [yangyi]);
      * @param item     商品信息
     * @param desc      商品描述信息
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/item/save")
    public TaotaoResult addItem(TbItem item,String desc){
        TaotaoResult result = itemService.addItem(item, desc);
        return result;
    }
}
