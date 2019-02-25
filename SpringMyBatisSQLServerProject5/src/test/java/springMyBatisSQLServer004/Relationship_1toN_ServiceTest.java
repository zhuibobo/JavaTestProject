package springMyBatisSQLServer004;

import org.junit.Test;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;
import springMyBatisSQLServerProject5.pojo.Relationship_1toN_cpu;
import springMyBatisSQLServerProject5.pojo.Relationship_1toN_pc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Relationship_1toN_ServiceTest {

    @Test
    public void insert2table() {
        Relationship_1toN_Service relationship_1ton_service=new Relationship_1toN_Service();
        insertNewPC(relationship_1ton_service);
        relationship_1ton_service.delete2table(relationship_1ton_service.getSessionFactory(),1);
    }

    private Relationship_1toN_pc insertNewPC(Relationship_1toN_Service relationship_1ton_service) {
        Relationship_1toN_pc relationship_1toN_pc=new Relationship_1toN_pc();
        relationship_1toN_pc.setId(1);
        relationship_1toN_pc.setName("我的电脑");
        List<Relationship_1toN_cpu> relationship_1toN_cpus=new ArrayList<>();

        Relationship_1toN_cpu relationship_1toN_cpu1=new Relationship_1toN_cpu();
        relationship_1toN_cpu1.setId(1);
        relationship_1toN_cpu1.setName("我的CPU1");
        relationship_1toN_cpu1.setPcId(relationship_1toN_pc.getId());

        Relationship_1toN_cpu relationship_1toN_cpu2=new Relationship_1toN_cpu();
        relationship_1toN_cpu2.setId(2);
        relationship_1toN_cpu2.setName("我的CPU2");
        relationship_1toN_cpu2.setPcId(relationship_1toN_pc.getId());

        relationship_1toN_cpus.add(relationship_1toN_cpu1);
        relationship_1toN_cpus.add(relationship_1toN_cpu2);
        relationship_1toN_pc.setCpus(relationship_1toN_cpus);

        relationship_1ton_service.insert2table(relationship_1ton_service.getSessionFactory(),relationship_1toN_pc);

        return relationship_1toN_pc;
    }

    @Test
    public void select2table001() {
        Relationship_1toN_Service relationship_1ton_service=new Relationship_1toN_Service();
        insertNewPC(relationship_1ton_service);
        Relationship_1toN_pc pc=relationship_1ton_service.select2table001(relationship_1ton_service.getSessionFactory(),1);
        assertEquals("我的电脑",pc.getName());
        assertEquals(2,pc.getCpus().size());
        relationship_1ton_service.delete2table(relationship_1ton_service.getSessionFactory(),1);
    }

    @Test
    public void select2table002() {
        Relationship_1toN_Service relationship_1ton_service=new Relationship_1toN_Service();
        insertNewPC(relationship_1ton_service);
        Relationship_1toN_pc pc=relationship_1ton_service.select2table002(relationship_1ton_service.getSessionFactory(),1);
        assertEquals("我的电脑",pc.getName());
        assertEquals(2,pc.getCpus().size());
        assertEquals(pc.getId(),pc.getCpus().get(0).getPcId());
        relationship_1ton_service.delete2table(relationship_1ton_service.getSessionFactory(),1);
    }
}