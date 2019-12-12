package com.taotao.search.erro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/11:17:31  <br/>
 * Description:全局异常
 */
@Component
public class ExceptionErroAll implements HandlerExceptionResolver {

    //log4j 是一个接口  实现类是Logger
    Logger logger = LoggerFactory.getLogger(ExceptionErroAll.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("系统发生异常",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统发生异常，请稍后重试");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
