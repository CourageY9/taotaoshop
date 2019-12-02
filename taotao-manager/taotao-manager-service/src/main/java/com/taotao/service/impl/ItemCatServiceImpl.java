package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/2:12:53  <br/>
 * Description:商品分类信息逻辑层
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * <pre>
     * Description :  获取商品分类信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/2 13:21 [yangyi]);
      * @param parentId
      * @return java.util.List<com.taotao.common.pojo.EasyUITreeNode>
     * </pre>
     */
    public List<EasyUITreeNode> getItemTree(long parentId) {
        List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
        List<TbItemCat> list = tbItemCatMapper.getItemCat(parentId);
        for (TbItemCat cat : list){
            EasyUITreeNode tree = new EasyUITreeNode();
            tree.setId(cat.getId());  //分类ID
            tree.setText(cat.getName());    //分类名称
            tree.setState(cat.getIsParent()?"closed":"open");    //是否有子节点
            resultList.add(tree);
        }
        return resultList;
    }
}
