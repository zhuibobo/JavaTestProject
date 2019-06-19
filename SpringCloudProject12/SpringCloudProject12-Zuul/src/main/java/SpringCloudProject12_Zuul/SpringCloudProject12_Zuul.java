package SpringCloudProject12_Zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudProject12_Zuul {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Zuul.class, args);
    }

}
