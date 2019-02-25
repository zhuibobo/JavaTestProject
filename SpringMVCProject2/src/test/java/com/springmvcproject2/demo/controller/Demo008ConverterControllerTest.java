package com.springmvcproject2.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo008ConverterControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void getPerson() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo008Converter/getPerson.do?person=孙悟空");
        requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
        Person person1=new Person("孙悟空","猴子",1000);
        ObjectMapper objectMapper = new ObjectMapper();
        String personJsons=objectMapper.writeValueAsString(person1);
        System.out.println(personJsons);
        mockMvc.perform(requestBuilder).andExpect(content().string(personJsons));
    }
}