package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:13:27  <br/>
 * Description:商品分类信息Controller层
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * <pre>
     * Description :  获取商品分类信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/2 13:34 [yangyi]);
      * @param parentId
      * @return java.util.List<com.taotao.common.pojo.EasyUITreeNode>
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/item/cat/list")
    //@RequestParam:接受参数 名称为id 默认值为 0 赋值给parentId
    public List<EasyUITreeNode> getItemTypeTree(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> result = itemCatService.getItemTree(parentId);
        return result;
    }
}
