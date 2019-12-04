package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    public List<EasyUITreeNode> getContentCategoryTree(long parentId);  //获取内容分类管理树形数据

    public TaotaoResult addContentCategoryTree(long parentId,String name);   //添加内容分类管理树形节点

    public TaotaoResult updateName(long id,String name);    //重命名

    public TaotaoResult deleteContentCategoryTree(long id);   //删除内容分类管理树形街道信息
}
