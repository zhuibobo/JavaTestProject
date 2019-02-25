package com.SpringCloudProject12_Server1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello1111!";
    }

    @RequestMapping("/login")
    public String login() {
        return "Login";
    }
}