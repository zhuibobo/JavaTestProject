package SpringCloudProject12_Config_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudProject12_Config_Server {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Config_Server.class, args);
    }

}
