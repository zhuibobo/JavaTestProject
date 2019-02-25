package javatestproject.springbootproject3.demo001bindproperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
@PropertySource("classpath:mail.properties")
public class DIConfig001 {

}
