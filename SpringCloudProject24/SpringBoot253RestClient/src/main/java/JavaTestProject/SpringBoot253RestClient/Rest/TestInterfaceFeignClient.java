package JavaTestProject.SpringBoot253RestClient.Rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="SpringBoot253RestInterface")
public interface TestInterfaceFeignClient {
    @GetMapping("/instanceId")
    String instanceId();
}
