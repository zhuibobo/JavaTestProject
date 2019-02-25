package com.springmvcproject2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/demo006File")
public class Demo006FileController {

    @RequestMapping("singleFile")
    @ResponseBody
    public String singleFile(@RequestParam("upload") MultipartFile upload,String name) {
        //FileUtils.writeByteArrayToFile(new File("e:/" + upload.getOriginalFilename()),upload.getBytes());
        return upload.getOriginalFilename() + name;
    }

    @RequestMapping("multipartFile")
    @ResponseBody
    public String multipartFile(HttpServletRequest request, HttpServletResponse response, String name) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        multipartRequest.getMultiFileMap();
        String fileNames="";
        if(!multipartRequest.getMultiFileMap().isEmpty()){
            for (Map.Entry<String, List<MultipartFile>> fileEntity : multipartRequest.getMultiFileMap().entrySet()) {
                String key=fileEntity.getKey();
                MultipartFile multipartFile = multipartRequest.getFile(key);
                fileNames+=key;
            }
        }
        //String fileNames="";
        return fileNames + name;
    }
}
