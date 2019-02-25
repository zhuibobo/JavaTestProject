package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo005ControllerAdviceControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void getName() throws Exception {
        mockMvc.perform(get("/demo005ControllerAdvice/getName.do?name=你是谁"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string("\"你是谁alex\""));
    }

    @Test
    public void unAuthorized() throws Exception {
        mockMvc.perform(get("/demo005ControllerAdvice/unAuthorized.do")).andExpect(view().name("UnauthenticatedExceptionView"));
    }

    @Test
    public void unAuthorizedJson() throws Exception {
        mockMvc.perform(get("/demo005ControllerAdvice/unAuthorizedJson.do")).andExpect(content().string("\"UnauthenticatedExceptionJson\""));
    }
}
