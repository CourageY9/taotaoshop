package com.taotao.portal.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/3:15:15  <br/>
 * Description:
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @Value("${AD1_CID}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;


    /**
     * <pre>
     * Description :  解决范文首页问题  <br/>
     * ChangeLog : 1. 创建 (2019/12/3 15:18 [yangyi]);
      *
      * @return java.lang.String
     * </pre>
     */
    @RequestMapping("/index")
    public String showIndex(Model model){
        EasyUIResult easyUIResult = contentService.getContent(AD1_CID);     //查询大广告的内容信息
        List<?> list = easyUIResult.getRows();
        List<TbContent> listContent = new ArrayList<TbContent>();
        listContent.addAll((Collection<? extends TbContent>) list);

        //放回给前端页面的数据信息
        List<Ad1Node> resultList = new ArrayList<>();
        for (TbContent tbContent : listContent){
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB(tbContent.getPic2());
            resultList.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(resultList));  //给model赋值，将resultList转为Json格式

        return "index";
    }
}
