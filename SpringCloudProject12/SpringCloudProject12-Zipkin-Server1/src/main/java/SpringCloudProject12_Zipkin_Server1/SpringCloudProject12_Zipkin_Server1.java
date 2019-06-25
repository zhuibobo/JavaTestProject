package SpringCloudProject12_Zipkin_Server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class SpringCloudProject12_Zipkin_Server1 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Zipkin_Server1.class, args);
    }
}
