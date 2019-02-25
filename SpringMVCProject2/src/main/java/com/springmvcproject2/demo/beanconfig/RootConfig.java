package com.springmvcproject2.demo.beanconfig;

import com.springmvcproject2.demo.service.ISpittleRepository;
import com.springmvcproject2.demo.service.impl.SpittleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/11
 * @Description:
 * @Version 1.0.0
 */
@Configuration
@ComponentScan(
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
    @Bean
    public ISpittleRepository getSpittleRepository(){
        return new SpittleRepository();
    }
}
