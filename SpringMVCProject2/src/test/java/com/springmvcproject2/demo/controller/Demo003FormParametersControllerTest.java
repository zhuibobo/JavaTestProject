package com.springmvcproject2.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
import com.springmvcproject2.demo.model.Family;
import com.springmvcproject2.demo.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/12
 * @Description:
 * @Version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo003FormParametersControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void convertToPersonVoParam() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/convertToPersonVoParam.do");
        requestBuilder.param("name","李雷");
        requestBuilder.param("sex","男");
        requestBuilder.param("age","10");
        Person person=new Person("李雷","男",10);
        ObjectMapper objectMapper = new ObjectMapper();
        String personJson=objectMapper.writeValueAsString(person);
        mockMvc.perform(requestBuilder).andExpect(content().string(personJson));
    }

    @Test
    public void convertToPersonVoParams() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/convertToPersonVoParams.do");
        requestBuilder.param("name","李雷");
        requestBuilder.param("sex","男");
        requestBuilder.param("age","10");
        Person person=new Person("李雷","男",10);
        Person[] persons=new Person[]{person,person};
        ObjectMapper objectMapper = new ObjectMapper();
        String personJsons=objectMapper.writeValueAsString(persons);
        System.out.println(personJsons);
        mockMvc.perform(requestBuilder).andExpect(content().string(personJsons));
    }

    @Test
    public void convertRequestBodyToPersonVoParams() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/convertRequestBodyToPersonVoParams.do");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
        Person person=new Person("李雷","男",10);
        Person[] persons=new Person[]{person,person};
        ObjectMapper objectMapper = new ObjectMapper();
        String personJsons=objectMapper.writeValueAsString(persons);
        requestBuilder.content(personJsons);
        System.out.println(personJsons);
        mockMvc.perform(requestBuilder).andExpect(content().string(personJsons));
    }

    @Test
    public void convertRequestBodyToFamilyVoParam() throws Exception{
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/convertRequestBodyToFamilyVoParam.do");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
        Person person=new Person("李雷","男",10);
        List<Person> personList=new ArrayList<Person>();
        personList.add(person);
        personList.add(person);
        Family family=new Family();
        family.setAddress("深圳市那个路");
        family.setPersonList(personList);
        ObjectMapper objectMapper = new ObjectMapper();
        String familyJson=objectMapper.writeValueAsString(family);
        requestBuilder.content(familyJson);
        System.out.println(familyJson);
        mockMvc.perform(requestBuilder).andExpect(content().string(familyJson));
    }

    @Test
    public void addPersonToFamily() throws Exception{
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/addPersonToFamily.do");
        //requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
        Person person=new Person("李雷","男",10);
        List<Person> personList=new ArrayList<Person>();
        personList.add(person);
        personList.add(person);
        Family family=new Family();
        family.setAddress("深圳市那个路");
        family.setPersonList(personList);
        ObjectMapper objectMapper = new ObjectMapper();
        String familyJson=objectMapper.writeValueAsString(family);

        Person wmmPerson=new Person("王梅梅","女",10);
        String wmmPersonJson=objectMapper.writeValueAsString(wmmPerson);
        System.out.println(familyJson);
        requestBuilder.param("family",familyJson);
        requestBuilder.param("person",wmmPersonJson);
        requestBuilder.param("num","1");
        personList.add(wmmPerson);
        String newFamilyJson=objectMapper.writeValueAsString(family);

        System.out.println(newFamilyJson);
        mockMvc.perform(requestBuilder).andExpect(content().string(newFamilyJson));
    }

    /*@Test
    public void convertToPersonWithValidationVoParam() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo003FormParameters/convertToPersonWithValidationVoParam.do");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
        requestBuilder.param("name","李雷");
        //PersonWithValidation person=new PersonWithValidation("李雷","男",10);
        //ObjectMapper objectMapper = new ObjectMapper();
        //String personJson=objectMapper.writeValueAsString(person);
        mockMvc.perform(requestBuilder).andExpect(content().string("\"error\""));
    }*/
}