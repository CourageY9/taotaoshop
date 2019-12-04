package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/4:10:52  <br/>
 * Description:
 */
@Controller
@RequestMapping("/content/")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * <pre>
     * Description :  通过categoryId获取Content内容  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 10:56 [yangyi]);
      * @param categoryId
      * @return com.taotao.common.pojo.EasyUIResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("query/list")
    public EasyUIResult getContent(long categoryId){
        try {
            EasyUIResult result = contentService.getContent(categoryId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * Description :  添加Content对象  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 11:38 [yangyi]);
      * @param tbContent
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("save")
    public TaotaoResult addContent(TbContent tbContent){
        try {
            TaotaoResult result = contentService.addContent(tbContent);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * Description :  删除商品  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 11:46 [yangyi]);
      * @param ids
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    @ResponseBody
    @RequestMapping("delete")
    public TaotaoResult deleteContent(long[] ids){
        try {
            TaotaoResult result = contentService.deleteContent(ids);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(300, "程序错误！！！");
    }

    /**
     * <pre>
     * Description :  暂时不做  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 14:54 [yangyi]);
      * @param tbContent
      * @return com.taotao.common.pojo.TaotaoResult
     * </pre>
     */
    public TaotaoResult updateContent(TbContent tbContent){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.build(300,"程序错误！！！" );
    }
}
