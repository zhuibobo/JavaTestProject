package SpringCloudProject12_Eureka_Consumer1.WebService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "spring-cloud-producer1")
public interface HelloRemote {
    @RequestMapping(value = "/Producer1/hello")
    public String hello(@RequestParam(value = "name") String name);
}
