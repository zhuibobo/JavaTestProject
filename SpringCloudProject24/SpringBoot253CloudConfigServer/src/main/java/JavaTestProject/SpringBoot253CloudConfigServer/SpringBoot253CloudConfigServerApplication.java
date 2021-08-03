package JavaTestProject.SpringBoot253CloudConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
public class SpringBoot253CloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot253CloudConfigServerApplication.class, args);
	}

}
