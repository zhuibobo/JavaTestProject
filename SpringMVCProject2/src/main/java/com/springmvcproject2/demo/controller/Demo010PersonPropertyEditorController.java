package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Person;
import com.springmvcproject2.demo.propertyeditor.PersonPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo010PersonPropertyEditor")
public class Demo010PersonPropertyEditorController {

    @InitBinder
    public void InitBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Person.class, new PersonPropertyEditor());
    }

    @RequestMapping("getPerson")
    @ResponseBody
    public Person getPerson(Person person) {
        return person;
    }
}
