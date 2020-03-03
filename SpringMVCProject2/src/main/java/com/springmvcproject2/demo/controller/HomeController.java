package com.springmvcproject2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/11
 * @Description:
 * @Version 1.0.0
 */
@Controller
public class HomeController {

    /*@Autowired
    WebApplicationContext webApplicationContext;*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        System.out.println("Home Controller Call");
        System.out.println(this.getClass());
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("intro-bg.jpg"));
        System.out.println(this.getClass().getClassLoader().getResource(""));
        System.out.println(this.getClass().getClassLoader().getResource("intro-bg.jpg"));
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        System.out.println(webApplicationContext.getServletContext().getRealPath("/"));
        return "home";
    }
}
