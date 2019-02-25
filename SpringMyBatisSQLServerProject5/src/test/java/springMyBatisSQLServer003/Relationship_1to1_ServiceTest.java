package springMyBatisSQLServer003;

import org.junit.Assert;
import org.junit.Test;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_cpu;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;

import static org.junit.Assert.*;

public class Relationship_1to1_ServiceTest {

    @Test
    public void insert2table() {
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        insertNewPC(relationship_1to1_service);
        relationship_1to1_service.delete2table001(relationship_1to1_service.getSessionFactory(),1);
    }

    @Test
    public void update2table() {
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        insertNewPC(relationship_1to1_service);
        Relationship_1to1_pc pc=relationship_1to1_service.select2table001(relationship_1to1_service.getSessionFactory(),1);
        pc.setName("我的新电脑");
        pc.getCpu().setName("我的新CPU");
        relationship_1to1_service.update2table(relationship_1to1_service.getSessionFactory(),pc);
        Relationship_1to1_pc newpc=relationship_1to1_service.select2table001(relationship_1to1_service.getSessionFactory(),1);
        Assert.assertEquals(newpc.getName(),pc.getName());
        Assert.assertEquals(newpc.getCpu().getName(),pc.getCpu().getName());
        relationship_1to1_service.delete2table001(relationship_1to1_service.getSessionFactory(),1);
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

    @Test
    public void select2table001() {
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        insertNewPC(relationship_1to1_service);
        Relationship_1to1_pc pc=relationship_1to1_service.select2table001(relationship_1to1_service.getSessionFactory(),1);
        relationship_1to1_service.delete2table001(relationship_1to1_service.getSessionFactory(),1);
        Assert.assertEquals("我的CPU",pc.getCpu().getName());
    }

    @Test
    public void select2table002() {
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        insertNewPC(relationship_1to1_service);
        Relationship_1to1_pc pc=relationship_1to1_service.select2table002(relationship_1to1_service.getSessionFactory(),1);
        relationship_1to1_service.delete2table001(relationship_1to1_service.getSessionFactory(),1);
        Assert.assertEquals("我的CPU",pc.getCpu().getName());
    }


}