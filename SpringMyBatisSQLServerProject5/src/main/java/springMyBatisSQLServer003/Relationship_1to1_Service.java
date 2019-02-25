package springMyBatisSQLServer003;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import springMyBatisSQLServerProject5.BaseDemo001Service;
import springMyBatisSQLServerProject5.dao.Relationship_1to1_cpuMapper;
import springMyBatisSQLServerProject5.dao.Relationship_1to1_pcMapper;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_cpu;
import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;

public class Relationship_1to1_Service extends BaseDemo001Service {
    public int insert2table(SqlSessionFactory sessionFactory, Relationship_1to1_pc pc){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        pcMapper.insert2table(pc);
        session.commit();
        session.close();
        return 0;
    }

    public void update2table(SqlSessionFactory sessionFactory, Relationship_1to1_pc pc){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        pcMapper.update2table(pc);
        session.commit();
        session.close();
    }

    public void delete2table001(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        Relationship_1to1_cpuMapper cpuMapper=session.getMapper(Relationship_1to1_cpuMapper.class);
        Relationship_1to1_pc pc=pcMapper.select2table001(id);
        pcMapper.deleteByPrimaryKey(id);
        cpuMapper.deleteByPrimaryKey(pc.getCpu().getId());
        session.commit();
        session.close();
    }

    public void deleteByPrimaryKey(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        pcMapper.deleteByPrimaryKey(id);
        session.commit();
        session.close();
    }

    public Relationship_1to1_pc select2table001(SqlSessionFactory sessionFactory, int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        Relationship_1to1_pc pc=pcMapper.select2table001(id);
        return pc;
    }

    public Relationship_1to1_pc select2table002(SqlSessionFactory sessionFactory, int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        return pcMapper.select2table002(id);
    }

    public Relationship_1to1_pc selectByPrimaryKey(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1to1_pcMapper pcMapper=session.getMapper(Relationship_1to1_pcMapper.class);
        return pcMapper.selectByPrimaryKey(id);
    }
}
