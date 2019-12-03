package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/3:20:53  <br/>
 * Description:商品管理分类Controller层
 */
@Controller
@RequestMapping("/content/category/")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * <pre>
     * Description :  获取内容分类管理树形结构表  <br/>
     * ChangeLog : 1. 创建 (2019/12/3 23:34 [yangyi]);
      * @param parentId
      * @return java.util.List<com.taotao.common.pojo.EasyUITreeNode>
     * </pre>
     */
    @ResponseBody
    @RequestMapping("list")
    public List<EasyUITreeNode> getTreeNode(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> result = contentCategoryService.getContentCategoryTree(parentId);
        return result;
    }
}
