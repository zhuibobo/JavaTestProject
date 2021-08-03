package JavaTestProject.SpringBoot253RestClient.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClient {
    //@Value("${eureka.instance.instance-id}")
    //private String instanceId;

    @Autowired
    TestInterfaceFeignClient testInterfaceFeignClient;

    @RequestMapping("/instanceId")
    public ResponseEntity<String> instanceId(){
        //List<String> resultData=discoveryClient.getServices();
        return ResponseEntity.ok(testInterfaceFeignClient.instanceId());
    }

    @RequestMapping("/SpringBoot253RestClient/instanceId")
    public ResponseEntity<String> springBoot253RestClientInstanceId(){
        //List<String> resultData=discoveryClient.getServices();
        return ResponseEntity.ok(testInterfaceFeignClient.instanceId());
    }
}
