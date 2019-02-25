package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.formatter.Customer;
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
@RequestMapping("/demo01101Redirect")
public class Demo011_1RedirectController {

    @RequestMapping(value = "redirectAndFlashObject",method = RequestMethod.GET)
    public String redirectAndFlashObject(RedirectAttributes model) {
        //http://localhost:8080/smvcp2/demo01101Redirect/redirectAndFlashObject.do
        Person person=new Person();
        person.setAge(19);
        person.setName("孙悟空");
        person.setSex("男");
        model.addFlashAttribute("person_swk",person);
        return "redirect:/demo01102Redirect/redirectAndGetFlashObject.do";
    }
}
