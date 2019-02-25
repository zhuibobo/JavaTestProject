package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.beanconfig.RootConfig;
import com.springmvcproject2.demo.beanconfig.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class Demo006FileControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void singleFile() throws Exception {
        //File f = new File("D:\\深圳社工.rar");
        //System.out.println(f.isFile()+"  "+f.getName()+f.exists());
        //FileInputStream fi1 = new FileInputStream(f);
        BufferedInputStream fi1= (BufferedInputStream) Demo006FileControllerTest.class.getResourceAsStream("/intro-bg.jpg");;
        //FileInputStream fi2 = new FileInputStream(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg"));
        MockMultipartFile fstmp = new MockMultipartFile("upload", "intro-bg.jpg", "multipart/form-data",fi1);
        //MockMultipartFile secmp = new MockMultipartFile("upload", "Tulips.jpg","multipart/form-data",fi2);
        //MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/demo006File/singleFile.do")
                .file(fstmp)
                .param("name","单个文件"))
                .andExpect(content().string("\"intro-bg.jpg单个文件\""));
    }

    @Test
    public void multipartFileFile() throws Exception {
        BufferedInputStream bi1= (BufferedInputStream) Demo006FileControllerTest.class.getResourceAsStream("/multipartFile1.jpg");
        BufferedInputStream bi2= (BufferedInputStream) Demo006FileControllerTest.class.getResourceAsStream("/multipartFile2.jpg");

        MockMultipartFile fstmp1 = new MockMultipartFile("multipartFile1", "multipartFile1.jpg", "multipart/form-data",bi1);
        MockMultipartFile fstmp2 = new MockMultipartFile("multipartFile2", "multipartFile1.jpg", "multipart/form-data",bi2);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/demo006File/multipartFile.do")
                .file(fstmp1).file(fstmp2)
                .param("name","多个文件"))
                .andExpect(content().string("\"multipartFile1multipartFile2多个文件\""));
    }
}
