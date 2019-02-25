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
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo004InterceptorControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void interceptorDemo1() throws Exception {
        mockMvc.perform(get("/demo004Interceptor/interceptorDemo1.do?name=你是谁&InterceptorDemo1=true"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string("InterceptorDemo1-Before\"你是谁\"InterceptorDemo1-After"));
    }
}