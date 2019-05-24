package SpringCloudProject12_Eureka_Consumer1.WebService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name= "spring-cloud-producer1",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    @RequestMapping(value = "/Producer1/hello")
    String hello(@RequestParam(value = "name") String name);
}
