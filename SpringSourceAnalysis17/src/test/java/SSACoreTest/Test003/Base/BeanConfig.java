package SSACoreTest.Test003.Base;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/28
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@PropertySource({"classpath:/SSACoreTest/Test003/config3.properties"})
public class BeanConfig {


    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("SSACoreTest/Test003/config4.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    /*@Bean
    public PropertySourcesPlaceholderConfigurer properties2() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        MutablePropertySources sources = new MutablePropertySources();
        try {
            //loader.load("")
            sources.addLast(loader.load("config5.yml", new ClassPathResource("SSACoreTest/Test003/config5.yml")).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configurer.setPropertySources(sources);
        return configurer;
    }*/
}
