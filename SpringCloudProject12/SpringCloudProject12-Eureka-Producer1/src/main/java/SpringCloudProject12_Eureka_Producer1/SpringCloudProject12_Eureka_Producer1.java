package SpringCloudProject12_Eureka_Producer1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudProject12_Eureka_Producer1 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Eureka_Producer1.class, args);
    }

}
