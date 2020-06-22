package SSAAOPTest.Test002;

import SSAAOPTest.Test001.Base.IPerson;
import SSAAOPTest.Test001.Base.Person;
import SSAAOPTest.Test001.Base.PersonProxy;
import SSAAOPTest.Test002.Base.ProxyFactory;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
public class Config002 {
    @Test
    public void config001(){
        IPerson person = new Person();

        IPerson factory = (IPerson) new ProxyFactory(person).getProxyInstance();

        factory.printName("Alex");
        System.out.println(factory.getClass().getName());
    }

}
