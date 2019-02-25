package com.springmvcproject2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/12
 * @Description:
 * @Version 1.0.0
 */

@Controller
@RequestMapping("/demo002PathParameters")
public class Demo002PathParametersController {

    @RequestMapping("getName/{name}")
    @ResponseBody
    public String getName(@PathVariable("name") String name) {
        return name;
    }
}
