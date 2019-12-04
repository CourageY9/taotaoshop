package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.dao.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * <pre>
     * Description :  获取内容分类管理树形数据  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 0:48 [yangyi]);
      * @param parentId
      * @return java.util.List<com.taotao.common.pojo.EasyUITreeNode>
     * </pre>
     */
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


    /**
     * <pre>
     * Description :  添加节点信息，并查询上一节是否父节点（不是就改为是）  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 1:22 [yangyi]);
      * @param parentId
     * @param name
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult addContentCategoryTree(long parentId, String name) {
        Date date = new Date();
        TbContentCategory tbcc = new TbContentCategory();
        tbcc.setParentId(parentId);     //设置parentId
        tbcc.setName(name);             //设置节点名称
        tbcc.setStatus(1);              //设置状态。可选值:1(正常),2(删除)
        tbcc.setSortOrder(1);           //设置排序号  默认1
        tbcc.setIsParent(false);        //该类目是否为父类目，1为true，0为false（先默认设置没有）
        tbcc.setCreated(date);          //设置创建时间
        tbcc.setUpdated(date);          //设置修改时间

        //添加节点信息(执行添加)
        int recordNum = tbContentCategoryMapper.addContentCategory(tbcc);

        //根据前端传来的parentId查询节点信息，如果是子节点就改为父节点
        TbContentCategory isTrue = tbContentCategoryMapper.findContentCategoryById(parentId);
        //判断查询到的对象是否为父类   是为true，否为false
        if(!isTrue.getIsParent()){
            isTrue.setIsParent(true);   //设置为父节点
            tbContentCategoryMapper.updateTrue(isTrue); //如果不是父节点，由于已经增加了子节点，所有要改成父节点
        }
        if (recordNum == 1){
            return TaotaoResult.ok(tbcc);   //返回添加的节点信息
        }
        return TaotaoResult.build(500, "添加失败，请重新添加");
    }


    /**
     * <pre>
     * Description :  重命名节点信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 9:08 [yangyi]);
      * @param id
     * @param name
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult updateName(long id, String name) {
        Date date = new Date();
        TbContentCategory tbcc = new TbContentCategory();
        tbcc.setId(id);
        tbcc.setName(name);
        tbcc.setUpdated(date);

        int recordNum = tbContentCategoryMapper.updateName(tbcc);
        if (recordNum == 1){
            return TaotaoResult.ok();
        }

        return TaotaoResult.build(500, "重命名失败");
    }


    /**
     * <pre>
     * Description :  删除节点信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 9:14 [yangyi]);
     * @param id
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult deleteContentCategoryTree (long id) {
        TbContentCategory tbcc = new TbContentCategory();

        //先查询parentId
        tbcc = tbContentCategoryMapper.findContentCategoryById(id);
        //在删除
        int resultNum = tbContentCategoryMapper.deleteContentCategoryById(id);
        if (resultNum == 1){
            return TaotaoResult.ok(tbcc);
        }
        return TaotaoResult.build(500, "删除失败");
    }
}
