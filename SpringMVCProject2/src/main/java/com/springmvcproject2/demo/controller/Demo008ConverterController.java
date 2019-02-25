package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo008Converter")
public class Demo008ConverterController {

    /*@InitBinder("user")
    public void customizeBinding (WebDataBinder binder) {
        binder.addCustomFormatter();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "dateOfBirth",
                new CustomDateEditor(dateFormatter, true));
    }*/

    @RequestMapping("getPerson")
    @ResponseBody
    public Person getPerson(Person person) {
        return person;
    }
}
