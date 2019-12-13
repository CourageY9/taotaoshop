package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/13:11:40  <br/>
 * Description:商品规格参数Controller
 */
@Controller
@RequestMapping("/item/param/")
public class ItemParamController {

    @Autowired
    private ItemParamServer itemParamServer;

    /**
     * <pre>
     * Description :  根据itemCatId 查询商品模板信息  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 11:48 [yangyi]);
      * @param itemCatId
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("query/itemcatid/{itemCatId}")
    public TaotaoResult getItemParam(@PathVariable long itemCatId){
        try {
            TaotaoResult result = itemParamServer.getItemParamByItemCatId(itemCatId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(500, "系统错误");
    }

    /**
     * <pre>
     * Description :  添加商品规格参数模板  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 14:23 [yangyi]);
      * @param cid
     * @param paramData
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("save/{cid}")
    public TaotaoResult addItemParam(@PathVariable long cid,String paramData){
        try {
            TbItemParam tbItemParam = new TbItemParam();
            tbItemParam.setItemCatId(cid);
            tbItemParam.setParamData(paramData);
            TaotaoResult result = itemParamServer.addItemParam(tbItemParam);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(500, "系统错误");
    }

    /**
     * <pre>
     * Description :  查询所有已存在的商品规格参数模板  <br/>
     * ChangeLog : 1. 创建 (2019/12/13 15:36 [yangyi]);
      *
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("list")
    public EasyUIResult getItemParamList(){
        try {
            EasyUIResult result = itemParamServer.showAll();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
