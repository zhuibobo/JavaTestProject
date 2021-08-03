package JavaTestProject.SpringBoot253RestInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBoot253RestInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot253RestInterfaceApplication.class, args);
	}

}
