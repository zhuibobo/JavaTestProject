package springMyBatisSQLServer002;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import springMyBatisSQLServerProject5.BaseDemo001Service;
import springMyBatisSQLServerProject5.dao.PersonFieldToPropertyMapper;
import springMyBatisSQLServerProject5.pojo.PersonFieldToProperty;

public class PersonFieldToPropertyService extends BaseDemo001Service {

    public void addPerson(SqlSessionFactory sessionFactory, PersonFieldToProperty person){
        SqlSession session = sessionFactory.openSession();
        PersonFieldToPropertyMapper personMapper=session.getMapper(PersonFieldToPropertyMapper.class);
        personMapper.insert(person);
        session.commit();
        session.close();
    }

    public void updatePerson(SqlSessionFactory sessionFactory,PersonFieldToProperty person){
        SqlSession session = sessionFactory.openSession();
        PersonFieldToPropertyMapper personMapper=session.getMapper(PersonFieldToPropertyMapper.class);
        personMapper.updateByPrimaryKeySelective(person);
        session.commit();
        session.close();
    }

    public PersonFieldToProperty select(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        PersonFieldToPropertyMapper personMapper=session.getMapper(PersonFieldToPropertyMapper.class);
        PersonFieldToProperty person=personMapper.selectByPrimaryKey(id);
        session.close();
        return person;
    }

    public void del(SqlSessionFactory sessionFactory,int id){
        SqlSession session = sessionFactory.openSession();
        PersonFieldToPropertyMapper personMapper=session.getMapper(PersonFieldToPropertyMapper.class);
        personMapper.deleteByPrimaryKey(id);
        session.commit();
        session.close();
    }

}
