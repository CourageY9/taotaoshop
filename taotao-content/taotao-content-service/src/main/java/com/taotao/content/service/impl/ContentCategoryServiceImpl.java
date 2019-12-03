package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.dao.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/3:18:06  <br/>
 * Description:
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    public List<EasyUITreeNode> getContentCategoryTree(long parentId) {
        List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
        List<TbContentCategory> list = tbContentCategoryMapper.getContentCategory(parentId);
        for (TbContentCategory tbcc : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbcc.getId());
            node.setText(tbcc.getName());
            node.setState(tbcc.getIsParent()?"closed":"open");
            resultList.add(node);
        }

        return resultList;
    }
}
