package SpringCloudProject12_Config_Client_Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudProject12_Config_Client_Eureka {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Config_Client_Eureka.class, args);
    }

}
