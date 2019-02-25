package springMyBatisSQLServer004;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import springMyBatisSQLServerProject5.BaseDemo001Service;
import springMyBatisSQLServerProject5.dao.Relationship_1toN_pcMapper;
import springMyBatisSQLServerProject5.pojo.Relationship_1toN_pc;

public class Relationship_1toN_Service extends BaseDemo001Service {
    public int insert2table(SqlSessionFactory sessionFactory, Relationship_1toN_pc pc){
        SqlSession session = sessionFactory.openSession();
        Relationship_1toN_pcMapper pcMapper=session.getMapper(Relationship_1toN_pcMapper.class);
        pcMapper.insert2table(pc);
        session.commit();
        session.close();
        return 0;
    }

    public void delete2table(SqlSessionFactory sessionFactory, int pc_id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1toN_pcMapper pcMapper=session.getMapper(Relationship_1toN_pcMapper.class);
        pcMapper.delete2table(pc_id);
        session.commit();
        session.close();
    }

    public Relationship_1toN_pc select2table001(SqlSessionFactory sessionFactory, int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1toN_pcMapper pcMapper=session.getMapper(Relationship_1toN_pcMapper.class);
        Relationship_1toN_pc pc=pcMapper.select2table001(id);
        return pc;
    }

    public Relationship_1toN_pc select2table002(SqlSessionFactory sessionFactory, int id){
        SqlSession session = sessionFactory.openSession();
        Relationship_1toN_pcMapper pcMapper=session.getMapper(Relationship_1toN_pcMapper.class);
        Relationship_1toN_pc pc=pcMapper.select2table002(id);
        return pc;
    }
}
