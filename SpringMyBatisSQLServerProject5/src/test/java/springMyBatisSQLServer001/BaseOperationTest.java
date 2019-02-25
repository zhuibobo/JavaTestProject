package springMyBatisSQLServer001;

import base.CalculateRunTimeDefault;
import base.ICalculateRunTime;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import springMyBatisSQLServerProject5.pojo.Person;

public class BaseOperationTest {

    @Test
    public void simpleTest() {
        PersonService baseOperation=new PersonService();
        SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();
        Person person = getPerson(1);
        baseOperation.addPerson(sqlSessionFactory,person);

        Person person1=baseOperation.select(sqlSessionFactory,person.getId());
        Assert.assertEquals(person.getName(),person1.getName());

        Person person2=new Person();
        person2.setId(person.getId());
        person2.setName("学生");
        baseOperation.updatePerson(sqlSessionFactory,person2);

        Person person3=baseOperation.select(sqlSessionFactory,person.getId());
        Assert.assertEquals(person2.getName(),person3.getName());

        baseOperation.del(sqlSessionFactory,person.getId());
    }

    private Person getPerson(int i) {
        Person person=new Person();
        person.setId(i);
        person.setAge(18);
        person.setIdcard("4451");
        person.setName("老师");
        person.setSex("男");
        return person;
    }

    @Test
    public void simpleTestInsert100() {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                PersonService baseOperation=new PersonService();
                SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();

                for(int i=0;i<100;i++){
                    Person person = getPerson(i);
                    baseOperation.addPerson(sqlSessionFactory,person);
                }

                baseOperation.delAll(sqlSessionFactory);
            }
        },"");
    }

    @Test
    public void simpleTestInsert1000() {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                PersonService baseOperation=new PersonService();
                SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();

                for(int i=0;i<1000;i++){
                    Person person = getPerson(i);
                    baseOperation.addPerson(sqlSessionFactory,person);
                }

                baseOperation.delAll(sqlSessionFactory);
            }
        },"");
    }

    @Test
    public void simpleTestInsert10000() {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                PersonService baseOperation=new PersonService();
                SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();

                for(int i=0;i<10000;i++){
                    Person person = getPerson(i);
                    baseOperation.addPerson(sqlSessionFactory,person);
                }

                baseOperation.delAll(sqlSessionFactory);
            }
        },"");
    }
}