package com.springboot2project11;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/8/20
 * To change this template use File | Settings | File Templates.
 */

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello1111!";
    }
}
