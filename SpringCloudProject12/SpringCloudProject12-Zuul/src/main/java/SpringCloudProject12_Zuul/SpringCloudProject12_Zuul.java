package SpringCloudProject12_Zuul;

import SpringCloudProject12_Zuul.CustFilter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages ={"SpringCloudProject12_Zuul"} )
@EnableZuulProxy
@EnableDiscoveryClient
public class SpringCloudProject12_Zuul {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProject12_Zuul.class, args);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
