package SpringCloudProject12_Config_Client.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
class HelloController {
    @Value("${neo.hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {

        String str = new String(this.hello.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        return str;
    }
}