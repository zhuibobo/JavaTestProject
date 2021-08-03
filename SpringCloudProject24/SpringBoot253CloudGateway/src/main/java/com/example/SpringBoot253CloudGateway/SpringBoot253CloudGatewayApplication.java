package com.example.SpringBoot253CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringBoot253CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot253CloudGatewayApplication.class, args);
	}
}
