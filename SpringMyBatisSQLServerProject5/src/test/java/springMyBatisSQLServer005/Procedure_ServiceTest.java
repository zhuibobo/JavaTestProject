package springMyBatisSQLServer005;

import org.junit.Test;
import springMyBatisSQLServer003.Relationship_1to1_Service;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Procedure_ServiceTest {

    @Test
    public void insertNotOut() {
        Procedure_Service procedure_service=new Procedure_Service();
        procedure_service.InsertNotOut(procedure_service.getSessionFactory(),1,"dov11","c1");
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        Relationship_1to1_pc relationship_1to1_pc=relationship_1to1_service.selectByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
        assertEquals("dov11",relationship_1to1_pc.getName());
        relationship_1to1_service.deleteByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
    }

    @Test
    public void sp_insert_relationship_1to1_pc_out_param_1() {
        Procedure_Service procedure_service=new Procedure_Service();
        Map data=procedure_service.sp_insert_relationship_1to1_pc_out_param_1(procedure_service.getSessionFactory(),1,"dov11","c1");
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        Relationship_1to1_pc relationship_1to1_pc=relationship_1to1_service.selectByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
        System.out.println(data.toString());
        assertEquals("dov11",relationship_1to1_pc.getName());
        assertEquals("outdov11",data.get("outname"));
        relationship_1to1_service.deleteByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
    }

    @Test
    public void sp_insert_relationship_1to1_pc_out_param_2() {
        Procedure_Service procedure_service=new Procedure_Service();
        List data=procedure_service.sp_insert_relationship_1to1_pc_out_param_2(procedure_service.getSessionFactory(),1,"dov11","c1");
        Relationship_1to1_Service relationship_1to1_service=new Relationship_1to1_Service();
        Relationship_1to1_pc relationship_1to1_pc=relationship_1to1_service.selectByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
        System.out.println(data.toString());
        //assertEquals("dov11",relationship_1to1_pc.getName());
        relationship_1to1_service.deleteByPrimaryKey(relationship_1to1_service.getSessionFactory(),1);
    }
}