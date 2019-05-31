package SpringCloudProject12_Eureka_Producer1.Controller;

import SpringCloudProject12_Eureka_Producer1.pojo.Person;
import SpringCloudProject12_Eureka_Producer1.service.PersonService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {

        PersonService baseOperation=new PersonService();
        SqlSessionFactory sqlSessionFactory=baseOperation.getSessionFactory();
        Person person = getPerson(1);
        baseOperation.addPerson(sqlSessionFactory,person);

        Person person1=baseOperation.select(sqlSessionFactory,person.getId());
        //Assert.assertEquals(person.getName(),person1.getName());

        Person person2=new Person();
        person2.setId(person.getId());
        person2.setName("学生");
        baseOperation.updatePerson(sqlSessionFactory,person2);

        return "hello "+name+"，this is first messge";
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
}
