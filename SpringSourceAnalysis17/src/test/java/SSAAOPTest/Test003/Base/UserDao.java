package SSAAOPTest.Test003.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("DB:保存用户"+person.getName());
    }

    @Autowired
    Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}