package SpringCloudProject12_Config_Server_Location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class SpringCloudProject12_Config_Server_Location {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Config_Server_Location.class, args);
        System.out.println("start");
    }

}
