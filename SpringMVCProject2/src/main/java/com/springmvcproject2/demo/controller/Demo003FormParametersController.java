package com.springmvcproject2.demo.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcproject2.demo.model.Family;
import com.springmvcproject2.demo.model.Person;
import com.springmvcproject2.demo.model.PersonWithValidation;
import com.springmvcproject2.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/12
 * @Description:
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/demo003FormParameters")
public class Demo003FormParametersController {

    @RequestMapping(value = "convertToPersonVoParam",method = RequestMethod.POST)
    @ResponseBody
    public Person convertToPersonVoParam(Person person) {
        return person;
    }

    @RequestMapping(value = "convertToPersonVoParams",method = RequestMethod.POST)
    @ResponseBody
    public Person[] convertToPersonVoParams(Person person1,Person person2) {
        return new Person[]{person1,person2};
    }

    @RequestMapping(value = "convertRequestBodyToPersonVoParams",method = RequestMethod.POST)
    @ResponseBody
    public List<Person> convertRequestBodyToPersonVoParams(@RequestBody List<Person> personList,HttpServletResponse response,HttpServletRequest request) {
        return personList;
    }

    @RequestMapping(value = "convertRequestBodyToFamilyVoParam",method = RequestMethod.POST)
    @ResponseBody
    public Family convertRequestBodyToFamilyVoParam(@RequestBody Family family, HttpServletResponse response, HttpServletRequest request) {
        return family;
    }

    @RequestMapping(value = "addPersonToFamily",method = RequestMethod.POST)
    @ResponseBody
    public Family addPersonToFamily(@RequestParam("family") String familyJson,@RequestParam("person") String personJson ,int num) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Family family=objectMapper.readValue(familyJson,Family.class);
        Person person=objectMapper.readValue(personJson,Person.class);
        family.getPersonList().add(person);
        //family.personList.add(person);
        return family;
    }

    @RequestMapping(value = "convertToPersonWithValidationVoParam")
    @ResponseBody
    public String convertToPersonWithValidationVoParam(@Valid PersonWithValidation personWithValidation, BindingResult result, ModelMap model) {
        //http://localhost:8080/smvcp2/demo003FormParameters/convertToPersonWithValidationVoParam.do?name=a
        if (result.hasErrors()) {
            return "error";
        }
        return "ok";
    }

}
