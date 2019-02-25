package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.exception.UnauthenticatedException;
import com.springmvcproject2.demo.exception.UnauthenticatedExceptionJson;
import com.springmvcproject2.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo005ControllerAdvice")
public class Demo005ControllerAdviceController {

    @RequestMapping("getName")
    @ResponseBody
    public String getName(String name, ModelMap model) {
        return name+((User)model.get("user")).getName();
    }

    @RequestMapping("unAuthorized")
    @ResponseBody
    public String unAuthorized() throws UnauthenticatedException {
        throw new UnauthenticatedException();
    }

    @RequestMapping("unAuthorizedJson")
    @ResponseBody
    public String unAuthorizedJson() throws UnauthenticatedExceptionJson {
        throw new UnauthenticatedExceptionJson();
    }
}
