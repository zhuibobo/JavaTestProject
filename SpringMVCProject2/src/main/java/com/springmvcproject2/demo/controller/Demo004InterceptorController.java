package com.springmvcproject2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo004Interceptor")
public class Demo004InterceptorController {

    @RequestMapping("interceptorDemo1")
    @ResponseBody
    public String interceptorDemo1(String name) {
        return name;
    }
}
