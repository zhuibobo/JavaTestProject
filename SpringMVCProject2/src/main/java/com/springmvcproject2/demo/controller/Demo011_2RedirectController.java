package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/16
 * @Description:
 * @Version 1.0.0
 */

@Controller
@RequestMapping("/demo01102Redirect")
public class Demo011_2RedirectController {
    @RequestMapping(value = "redirectAndGetFlashObject",method = RequestMethod.GET)
    @ResponseBody
    public Person redirectAndGetFlashObject(ModelMap model) {
        Person person= (Person) model.get("person_swk");
        return person;
    }
}
