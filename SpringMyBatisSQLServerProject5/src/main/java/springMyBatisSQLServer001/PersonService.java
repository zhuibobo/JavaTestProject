package springMyBatisSQLServer001;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import springMyBatisSQLServerProject5.BaseDemo001Service;
import springMyBatisSQLServerProject5.dao.PersonMapper;
import springMyBatisSQLServerProject5.pojo.Person;

import java.io.InputStream;

public class PersonService extends BaseDemo001Service {

    public void addPerson(SqlSessionFactory sessionFactory,Person person){
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
