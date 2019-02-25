package com.springmvcproject2.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
import com.springmvcproject2.demo.formatter.Address;
import com.springmvcproject2.demo.formatter.Customer;
import com.springmvcproject2.demo.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo009FormatterControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }


    @Test
    public void convertToCustomerVoParamFullAddressCustomer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo009Formatter/convertToCustomerVoParamFullAddressCustomer.do");

        requestBuilder.param("id","001");
        requestBuilder.param("name","猪八戒");
        requestBuilder.param("address","1,2,3,4");
        requestBuilder.param("sumbitDate","2017-12-14");
        //Person person=new Person("李雷","男",10);
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer=new Customer();
        customer.setId(001l);
        customer.setName("猪八戒");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2017-12-14");
        customer.setSumbitDate(myDate1);
        Address address=new Address();
        address.setStreet("1");
        address.setCity("2");
        address.setZipCode("3");
        address.setCounty("4");
        customer.setAddress(address);
        String customerJson=objectMapper.writeValueAsString(customer);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        String serverPersonJson=mvcResult.getResponse().getContentAsString();
        System.out.println(serverPersonJson);
        Assert.assertEquals(customerJson,serverPersonJson);
    }

    @Test
    public void convertToCustomerVoParamRegionAddressCustomer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =post("/demo009Formatter/convertToCustomerVoParamRegionAddressCustomer.do");

        requestBuilder.param("id","001");
        requestBuilder.param("name","猪八戒");
        requestBuilder.param("address","1,2,3");
        //Person person=new Person("李雷","男",10);
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer=new Customer();
        customer.setId(001l);
        customer.setName("猪八戒");
        Address address=new Address();
        address.setStreet(null);
        address.setCity("1");
        address.setZipCode("2");
        address.setCounty("3");
        customer.setAddress(address);
        String customerJson=objectMapper.writeValueAsString(customer);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        String serverPersonJson=mvcResult.getResponse().getContentAsString();
        System.out.println(serverPersonJson);
        Assert.assertEquals(customerJson,serverPersonJson);
    }
}