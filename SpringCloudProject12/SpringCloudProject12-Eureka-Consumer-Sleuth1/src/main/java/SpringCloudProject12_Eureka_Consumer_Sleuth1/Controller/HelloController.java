package SpringCloudProject12_Eureka_Consumer_Sleuth1.Controller;

import SpringCloudProject12_Eureka_Consumer_Sleuth1.WebServiceToConsumer1.ToProducer1HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    ToProducer1HelloRemote toProducer1HelloRemote;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return toProducer1HelloRemote.hello(name);
    }

}
