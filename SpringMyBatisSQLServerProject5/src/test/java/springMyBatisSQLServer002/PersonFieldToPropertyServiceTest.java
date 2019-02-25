package springMyBatisSQLServer002;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import springMyBatisSQLServerProject5.pojo.PersonFieldToProperty;

import static org.junit.Assert.*;

public class PersonFieldToPropertyServiceTest {

    @Test
    public void simpleTest() {
        PersonFieldToPropertyService baseOperation=new PersonFieldToPropertyService();
        SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();
        PersonFieldToProperty person=new PersonFieldToProperty();
        person.setPerson_id(1);
        person.setAge(18);
        person.setIdcard("4451");
        person.setPerson_name("老师");
        person.setSex("男");
        baseOperation.addPerson(sqlSessionFactory,person);

        PersonFieldToProperty person1=baseOperation.select(sqlSessionFactory,person.getPerson_id());
        Assert.assertEquals(person.getPerson_name(),person1.getPerson_name());

        PersonFieldToProperty person2=new PersonFieldToProperty();
        person2.setPerson_id(person.getPerson_id());
        person2.setPerson_name("学生");
        baseOperation.updatePerson(sqlSessionFactory,person2);

        PersonFieldToProperty person3=baseOperation.select(sqlSessionFactory,person.getPerson_id());
        Assert.assertEquals(person2.getPerson_name(),person3.getPerson_name());

        baseOperation.del(sqlSessionFactory,person.getPerson_id());
    }
}