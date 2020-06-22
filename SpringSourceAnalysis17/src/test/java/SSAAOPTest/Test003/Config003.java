package SSAAOPTest.Test003;

import SSAAOPTest.Test001.Base.IPerson;
import SSAAOPTest.Test001.Base.Person;
import SSAAOPTest.Test003.Base.IUserDao;
import SSAAOPTest.Test003.Base.ProxyFactory;
import SSAAOPTest.Test003.Base.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
public class Config003 {
    @Test
    public void config001() throws Exception {
        System.out.println("现在开始初始化容器");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("SSAAOPTest.Test003.Base");

        ProxyFactory<IUserDao> proxyFactory=applicationContext.getBean(ProxyFactory.class);
        IUserDao userProxyDao=proxyFactory.createProxyInstance(IUserDao.class);
        userProxyDao.save();
    }

}
