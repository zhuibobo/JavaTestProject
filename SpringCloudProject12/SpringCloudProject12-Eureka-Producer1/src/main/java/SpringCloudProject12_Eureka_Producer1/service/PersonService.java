package SpringCloudProject12_Eureka_Producer1.service;

import SpringCloudProject12_Eureka_Producer1.dao.PersonMapper;
import SpringCloudProject12_Eureka_Producer1.pojo.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PersonService extends BaseService {

    public void addPerson(SqlSessionFactory sessionFactory, Person person){
        SqlSession session = sessionFactory.openSession();
        PersonMapper personMapper=session.getMapper(PersonMapper.class);
        personMapper.insert(person);
        session.commit();
        session.close();
    }

    public void updatePerson(SqlSessionFactory sessionFactory,Person person){
        SqlSession session = sessionFactory.openSession();
        PersonMapper personMapper=session.getMapper(PersonMapper.class);
        personMapper.updateByPrimaryKeySelective(person);
        session.commit();
        session.close();
    }

    public Person select(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        PersonMapper personMapper=session.getMapper(PersonMapper.class);
        Person person=personMapper.selectByPrimaryKey(id);
        session.close();
        return person;
    }

    public void del(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        PersonMapper personMapper=session.getMapper(PersonMapper.class);
        personMapper.deleteByPrimaryKey(id);
        session.commit();
        session.close();
    }

    public void delAll(SqlSessionFactory sessionFactory){
        SqlSession session = sessionFactory.openSession();
        PersonMapper personMapper=session.getMapper(PersonMapper.class);
        personMapper.deleteAll();
        session.commit();
        session.close();
    }
}
