package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/demo007ModelAttribute")
public class Demo007ModelAttributeController {

    @ModelAttribute
    public Person addAccount1(String name) {
        Person person=new Person(name,"男",18);
        return person;
    }

    @ModelAttribute
    public void addAccount2(String name,Model model) {
        Person person=new Person(name,"女",18);
        model.addAttribute("addAccount2",person);
    }

    @RequestMapping("getPerson")
    @ResponseBody
    public List<Person> getPerson(ModelMap model) {
        List<Person> personList=new ArrayList<>();
        personList.add((Person) model.get("person"));
        personList.add((Person) model.get("addAccount2"));
        return personList;
    }

    @RequestMapping("getPersonFromModel")
    @ResponseBody
    public Person getPersonFromModel(@ModelAttribute Person person) {
        return person;
    }

    @RequestMapping("getPersonFromModelBindByUrl/{name}/{sex}/{age}")
    @ResponseBody
    public Person getPersonFromModelBindByUrl(@ModelAttribute Person person) {
        return person;
    }

    @RequestMapping("getPersonFromConvert/{name}")
    @ResponseBody
    public Person getPersonFromConvert(@ModelAttribute("name") Person person) {
        return person;
    }
}
