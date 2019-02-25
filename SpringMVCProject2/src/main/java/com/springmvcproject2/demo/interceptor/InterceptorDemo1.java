package com.springmvcproject2.demo.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorDemo1 extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getParameterMap().containsKey("InterceptorDemo1")){
            response.getWriter().print("InterceptorDemo1-Before");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getParameterMap().containsKey("InterceptorDemo1")){
            response.getWriter().print("InterceptorDemo1-After");
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
