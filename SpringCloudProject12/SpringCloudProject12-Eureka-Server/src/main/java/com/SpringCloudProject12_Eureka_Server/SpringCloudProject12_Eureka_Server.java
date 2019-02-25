package com.SpringCloudProject12_Eureka_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudProject12_Eureka_Server {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Eureka_Server.class, args);
    }
}
