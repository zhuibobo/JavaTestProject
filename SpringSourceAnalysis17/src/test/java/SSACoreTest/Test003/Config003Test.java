package SSACoreTest.Test003;

import SSACoreTest.Test003.Base.IPerson;
import SSACoreTest.Test003.Base.Work;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/19
 * To change this template use File | Settings | File Templates.
 */
public class Config003Test {

    @Test
    public void config001(){
        System.out.println("现在开始初始化容器");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("SSACoreTest.Test003");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Work work=applicationContext.getBean(Work.class);
        work.work();

        IPerson person1=(IPerson)applicationContext.getBean("person1");
        System.out.println(person1.getName());

        IPerson person3=(IPerson)applicationContext.getBean("person3");
        System.out.println(person3.getName());

        System.out.println("现在开始关闭容器！");
        ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
    }
}
