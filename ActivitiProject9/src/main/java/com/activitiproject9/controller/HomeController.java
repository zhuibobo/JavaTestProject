package com.activitiproject9.controller;

import com.activitiproject9.services.First;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        System.out.println("Home Controller Call");
        First first=new First();
        first.run();
        return "home";
    }
}