package SpringCloudProject12_Eureka_Consumer1.Controller;

import SpringCloudProject12_Eureka_Consumer1.WebService.HelloRemote;
import SpringCloudProject12_Eureka_Consumer1.pojo.Relationship_1to1_cpu;
import SpringCloudProject12_Eureka_Consumer1.pojo.Relationship_1to1_pc;
import SpringCloudProject12_Eureka_Consumer1.service.Relationship_1to1_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {

    @Autowired
    SpringCloudProject12_Eureka_Consumer1.WebService.HelloRemote HelloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {

        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        insertNewPC(relationship_1to1_service);

        return HelloRemote.hello(name);
    }

    private Relationship_1to1_pc insertNewPC(Relationship_1to1_Service relationship_1to1_service) {
        Relationship_1to1_pc relationship_1to1_pc=new Relationship_1to1_pc();
        relationship_1to1_pc.setId(1);
        relationship_1to1_pc.setName("我的电脑");
        Relationship_1to1_cpu relationship_1to1_cpu=new Relationship_1to1_cpu();
        relationship_1to1_cpu.setId(1);
        relationship_1to1_cpu.setName("我的CPU");
        relationship_1to1_pc.setCpu(relationship_1to1_cpu);
        relationship_1to1_service.insert2table(relationship_1to1_service.getSessionFactory(),relationship_1to1_pc);
        return relationship_1to1_pc;
    }
}
