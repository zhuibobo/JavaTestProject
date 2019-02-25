package com.springmvcproject2.demo.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
import com.springmvcproject2.demo.model.Person;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo001QueryParametersControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void getName() throws Exception {
        mockMvc.perform(get("/demo001QueryParameters/getName.do?name=你是谁"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string("\"你是谁\""));
    }

    @Test
    public void getPerson() throws Exception {
        mockMvc.perform(get("/demo001QueryParameters/getPerson.do?name=李雷&sex=男&age=10"))
                .andExpect(content().string("{\"name\":\"李雷\",\"sex\":\"男\",\"age\":10}"));
    }

    @Test
    public void getPersonVoToJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person=new Person("李雷","男",10);
        String personJson=objectMapper.writeValueAsString(person);
        System.out.println(personJson);
        mockMvc.perform(get("/demo001QueryParameters/getPerson.do?name=李雷&sex=男&age=10"))
                .andExpect(content().string(personJson));
    }

    @Test
    public void getPersonByString() throws Exception {
        MvcResult mvcResult=mockMvc.perform(get("/demo001QueryParameters/getPerson.do?name=李雷&sex=男&age=10")).andReturn();
        String personJson=mvcResult.getResponse().getContentAsString();
        System.out.println(personJson);
    }

    @Test
    public void getPersonStringToVo() throws Exception {
        MvcResult mvcResult=mockMvc.perform(get("/demo001QueryParameters/getPerson.do?name=李雷&sex=男&age=10")).andReturn();
        String personJson=mvcResult.getResponse().getContentAsString();
        System.out.println(personJson);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Person person=objectMapper.readValue(personJson,Person.class);
        Assert.assertEquals("李雷",person.getName());
    }

    @Test
    public void getPersonByVo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person=new Person("李雷","男",10);
        String personJson=objectMapper.writeValueAsString(person);
        System.out.println(personJson);
        MvcResult mvcResult=mockMvc.perform(get("/demo001QueryParameters/getPersonByVo.do?name=李雷&sex=男&age=10")).andReturn();
        String personResultJson=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(personResultJson,personJson);
    }

    @Test
    public void getStringOnly() throws Exception {
        mockMvc.perform(get("/demo001QueryParameters/getNotDoubleQuotesString.do?name=李雷"))
                .andExpect(content().string("李雷"));
    }
}