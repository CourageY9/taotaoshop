package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {

    public List<EasyUITreeNode> getContentCategoryTree(long parentId);
}
