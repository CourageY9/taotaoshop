package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/1:17:07  <br/>
 * Description:页面跳转Controller
 */
@Controller
public class PageController {

    /**
     * <pre>
     * Description :  设置默认访问index页面  <br/>
     * ChangeLog : 1. 创建 (2019/12/1 17:09 [yangyi]);
      * @return java.lang.String
     * </pre>
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * <pre>
     * Description :  万能页面跳转  <br/>
     * ChangeLog : 1. 创建 (2019/12/1 17:17 [yangyi]);
      * @param page
      * @return java.lang.String
     * </pre>
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
