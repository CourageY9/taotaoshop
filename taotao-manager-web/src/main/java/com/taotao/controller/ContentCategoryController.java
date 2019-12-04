package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.apache.zookeeper.data.Id;
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
@RequestMapping("/content/category")
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
    @RequestMapping("/list")
    public List<EasyUITreeNode> getTreeNode(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EasyUITreeNode> result = contentCategoryService.getContentCategoryTree(parentId);
        return result;
    }

    /**
     * <pre>
     * Description :  添加节点信息，并查询上一节是否父节点（不是就改为是）  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 1:24 [yangyi]);
      * @param parentId
     * @param name
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/create")
    public TaotaoResult addContentCategoryTree(@RequestParam(value = "parentId",defaultValue = "0") long parentId,String name){
        TaotaoResult result = contentCategoryService.addContentCategoryTree(parentId, name);
        return result;
    }

    /**
     * <pre>
     * Description :  内容分类管理信息重命名  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 1:56 [yangyi]);
      * @param id
     * @param name
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/update")
    public TaotaoResult updateContentCategoryName(long id,String name){
        try {
            TaotaoResult result = contentCategoryService.updateName(id, name);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(500, "重命名失败");
    }

    /**
     * <pre>
     * Description :  删除节点信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 9:57 [yangyi]);
      * @param id
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("/delete")
    public TaotaoResult deleteContentCategoryName(long id){
        try {
            TaotaoResult result = contentCategoryService.deleteContentCategoryTree(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(300, "系统错误");
    }
}
