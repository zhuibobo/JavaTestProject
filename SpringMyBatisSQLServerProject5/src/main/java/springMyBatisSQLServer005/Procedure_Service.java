package springMyBatisSQLServer005;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import springMyBatisSQLServerProject5.BaseDemo001Service;
import springMyBatisSQLServerProject5.dao.ProcedureMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Procedure_Service extends BaseDemo001Service {
    void InsertNotOut(SqlSessionFactory sessionFactory, int id, String name,String cpuid){
        SqlSession session = sessionFactory.openSession();
        ProcedureMapper procMapper=session.getMapper(ProcedureMapper.class);
        Map<String,Object> data=new HashMap();
        data.put("id",id);
        data.put("name",name);
        data.put("cpuid",cpuid);
        procMapper.insertRecord(data);
        session.commit();
        session.close();
    }

    Map sp_insert_relationship_1to1_pc_out_param_1(SqlSessionFactory sessionFactory, int id, String name,String cpuid){
        SqlSession session = sessionFactory.openSession();
        ProcedureMapper procMapper=session.getMapper(ProcedureMapper.class);
        Map<String,Object> data=new HashMap();
        data.put("id",id);
        data.put("name",name);
        data.put("cpuid",cpuid);
        //data.put("outname","outname");
        Map result=procMapper.sp_insert_relationship_1to1_pc_out_param_1(data);
        session.commit();
        session.close();
        return result;
    }

    List sp_insert_relationship_1to1_pc_out_param_2(SqlSessionFactory sessionFactory, int id, String name, String cpuid){
        SqlSession session = sessionFactory.openSession();
        ProcedureMapper procMapper=session.getMapper(ProcedureMapper.class);
        Map<String,Object> data=new HashMap();
        data.put("id",id);
        data.put("name",name);
        data.put("cpuid",cpuid);
        //data.put("outname","outname");
        List result=procMapper.sp_insert_relationship_1to1_pc_out_param_2(data);
        //String s1="springMyBatisSQLServerProject5.dao.ProcedureMapper.sp_insert_relationship_1to1_pc_out_param_2";
        //List<List<?>> list1=session.selectList(s1,data);
        session.commit();
        session.close();
        return result;
    }
}
