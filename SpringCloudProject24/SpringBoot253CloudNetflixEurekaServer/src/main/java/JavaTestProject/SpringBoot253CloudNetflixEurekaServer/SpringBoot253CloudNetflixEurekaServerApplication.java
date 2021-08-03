package JavaTestProject.SpringBoot253CloudNetflixEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBoot253CloudNetflixEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot253CloudNetflixEurekaServerApplication.class, args);
	}

}
