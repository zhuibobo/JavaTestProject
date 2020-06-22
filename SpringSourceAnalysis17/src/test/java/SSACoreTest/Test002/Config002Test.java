package SSACoreTest.Test002;

import SSACoreTest.Test001.base.BeanLifeBean;
import SSACoreTest.Test001.base.SimpleBean;
import SSACoreTest.Test002.base.OrderService;
import SSACoreTest.Test002.base.Person;
import SSACoreTest.Test002.base.PersonCreatedByPostProcessBeforeInstantiation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/19
 * To change this template use File | Settings | File Templates.
 */
public class Config002Test {

    @Test
    public void config001(){
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("SSACoreTest/Test002/config002.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);

        OrderService orderService = factory.getBean("orderService",OrderService.class);
        orderService.hello();
        System.out.println(orderService);

        PersonCreatedByPostProcessBeforeInstantiation personCreatedByPostProcessBeforeInstantiation = factory.getBean("personCreatedByPostProcessBeforeInstantiation",PersonCreatedByPostProcessBeforeInstantiation.class);
        personCreatedByPostProcessBeforeInstantiation.hello();

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }

    @Test
    public void config002(){
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new AnnotationConfigApplicationContext("SSACoreTest.Test002.base");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean(Person.class);
        System.out.println(person);

        OrderService orderService = factory.getBean("orderService",OrderService.class);
        orderService.hello();
        System.out.println(orderService);

        PersonCreatedByPostProcessBeforeInstantiation personCreatedByPostProcessBeforeInstantiation = factory.getBean("personCreatedByPostProcessBeforeInstantiation",PersonCreatedByPostProcessBeforeInstantiation.class);
        personCreatedByPostProcessBeforeInstantiation.hello();

        System.out.println("现在开始关闭容器！");
        ((AnnotationConfigApplicationContext)factory).registerShutdownHook();
    }
}
