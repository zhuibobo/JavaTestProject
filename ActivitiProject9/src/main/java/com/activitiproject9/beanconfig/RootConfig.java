package com.activitiproject9.beanconfig;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
    /*public StandaloneProcessEngineConfiguration getProcessEngineConfiguration(){
        StandaloneProcessEngineConfiguration standaloneProcessEngineConfiguration=new StandaloneProcessEngineConfiguration();
        standaloneProcessEngineConfiguration.setJdbcUrl("jdbc:sqlserver://localhost:1433; DatabaseName=ActivitiProject9");
        standaloneProcessEngineConfiguration.setJdbcDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        standaloneProcessEngineConfiguration.setJdbcUsername("sa");
        standaloneProcessEngineConfiguration.setJdbcPassword("sql");
        standaloneProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
        return standaloneProcessEngineConfiguration;
    }*/
}
