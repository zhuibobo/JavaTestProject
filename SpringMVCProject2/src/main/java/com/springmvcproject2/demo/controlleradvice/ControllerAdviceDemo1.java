package com.springmvcproject2.demo.controlleradvice;

import com.springmvcproject2.demo.controller.Demo005ControllerAdviceController;
import com.springmvcproject2.demo.exception.UnauthenticatedException;
import com.springmvcproject2.demo.exception.UnauthenticatedExceptionJson;
import com.springmvcproject2.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice(assignableTypes={Demo005ControllerAdviceController.class})
public class ControllerAdviceDemo1 {

    @ModelAttribute
    public User newUser() {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return new User("alex",22);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public String processUnauthenticatedException(NativeWebRequest request, UnauthenticatedException e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
        return "UnauthenticatedExceptionView"; //返回一个逻辑视图名
    }

    @ExceptionHandler(UnauthenticatedExceptionJson.class)
    @ResponseBody
    public String processUnauthenticatedExceptionJson(NativeWebRequest request, UnauthenticatedExceptionJson e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedExceptionJson异常时执行");
        return "UnauthenticatedExceptionJson"; //返回一个逻辑视图名
    }
}
