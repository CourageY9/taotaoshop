package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/3:15:15  <br/>
 * Description:
 */
@Controller
public class IndexController {

    /**
     * <pre>
     * Description :  解决范文首页问题  <br/>
     * ChangeLog : 1. 创建 (2019/12/3 15:18 [yangyi]);
      *
      * @return java.lang.String
     * </pre>
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
