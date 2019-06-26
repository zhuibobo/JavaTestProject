package SpringCloudProject12_Eureka_Consumer1.Controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ToProducer1HelloController {

    @Autowired
    SpringCloudProject12_Eureka_Consumer1.WebService.HelloRemote HelloRemote;

    @RequestMapping("/ToProducer1Hello")
    public String index(@RequestParam String name) {
        return HelloRemote.hello(name);
    }

}
