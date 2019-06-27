package SpringCloudProject12_Eureka_Consumer_Sleuth1.WebServiceToConsumer1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name= "spring-cloud-consumer1", configuration = FeignLogConfiguration.class)
public interface ToProducer1HelloRemote {
    @RequestMapping(value = "/Consumer1/ToProducer1Hello")
    String hello(@RequestParam(value = "name") String name);
}
