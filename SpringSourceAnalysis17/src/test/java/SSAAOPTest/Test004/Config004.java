package SSAAOPTest.Test004;

import SSAAOPTest.Test004.Base.DoSthService;
import SSAAOPTest.Test004.Base.IPerson;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
public class Config004 {
    @Test
    public void config001() throws Exception {
        System.out.println("现在开始初始化容器");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("SSAAOPTest.Test004.Base");

        IPerson person=applicationContext.getBean(IPerson.class);

        DoSthService doSthService=(DoSthService)person;

        person.printName("");
        doSthService.doSth();
    }

    @Test
    public void config002() throws Exception {
        System.out.println("-----------------------------------------------现在开始初始化容器----------------------------------------------------------");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("SSAAOPTest.Test004.Base");

        IPerson person=(IPerson)applicationContext.getBean("cnPerson");
        String name=person.printName("source");
        System.out.println("***************************"+name+"***************************");
    }

    @Test
    public void config003() throws Exception {
        System.out.println("-----------------------------------------------现在开始初始化容器----------------------------------------------------------");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("SSAAOPTest.Test004.Base");

        IPerson person=(IPerson)applicationContext.getBean("annPerson");
        String name=person.printName("annPerson source");
        System.out.println("***************************"+name+"***************************");
    }
}
