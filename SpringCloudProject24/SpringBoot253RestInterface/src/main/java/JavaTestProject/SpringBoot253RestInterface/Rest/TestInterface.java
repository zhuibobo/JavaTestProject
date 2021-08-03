package JavaTestProject.SpringBoot253RestInterface.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestInterface {

    private final DiscoveryClient discoveryClient;

    public TestInterface(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/index")
    public ResponseEntity<Map<String,String>> index(){
        String resultData="222";
        HashMap<String,String> data=new HashMap<>();
        data.put("key1","key1");
        data.put("key2","key2");
        return ResponseEntity.ok(data);
        //return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping("/string")
    public ResponseEntity<String> string(){
        String resultData="Greetings from Spring Boot!";
        return ResponseEntity.ok(resultData);
    }

    @RequestMapping("/discoveryClient")
    public ResponseEntity<List<String>> discoveryClient(){
        List<String> resultData=discoveryClient.getServices();
        return ResponseEntity.ok(resultData);
    }

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping("/instanceId")
    public ResponseEntity<String> instanceId() throws Exception {
        //List<String> resultData=discoveryClient.getServices();
        if(instanceId.equals("client-for-rest-interface-instance-18092-2")){
            Thread.sleep(60*1000);
        }
        return ResponseEntity.ok(instanceId);
    }

    @RequestMapping("/springBoot253RestInterface/instanceId")
    public ResponseEntity<String> springBoot253RestInterfaceInstanceId() throws Exception {
        return ResponseEntity.ok(instanceId);
    }
}
