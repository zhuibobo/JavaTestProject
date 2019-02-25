package com.springmvcproject2.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        HomeController controller = new HomeController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void home() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }
}