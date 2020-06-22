package SSAAOPTest.Test001;

import SSAAOPTest.Test001.Base.IPerson;
import SSAAOPTest.Test001.Base.Person;
import SSAAOPTest.Test001.Base.PersonProxy;
import SSACoreTest.Test001.base.SimpleBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
public class Config001 {
    @Test
    public void config001(){
        IPerson person=new Person();
        person.printName("alex");
    }

    @Test
    public void config002(){
        PersonProxy personProxy=new PersonProxy();
        IPerson person=personProxy.getProxy();
        person.printName("alex");
    }
}
