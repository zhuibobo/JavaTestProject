package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/demo001QueryParameters")
public class Demo001QueryParametersController {

    @RequestMapping("getName")
    @ResponseBody
    public String getName(String name) {
        return name;
    }

    @RequestMapping("getPerson")
    @ResponseBody
    public Person getPerson(String name, String sex,int age) {
        return new Person(name,sex,age);
    }

    @RequestMapping("getPersonByVo")
    @ResponseBody
    public Person getPersonByVo(Person person,String name, String sex,int age ) {
        return person;
    }

    @RequestMapping(value="getNotDoubleQuotesString")
    @ResponseBody
    public void getNotDoubleQuotesString(String name, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(name);
    }
}
