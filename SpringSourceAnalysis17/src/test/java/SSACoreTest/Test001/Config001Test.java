package SSACoreTest.Test001;

import SSACoreTest.Test001.base.BeanLifeBean;
import SSACoreTest.Test001.base.SimpleBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/19
 * To change this template use File | Settings | File Templates.
 */
public class Config001Test {

    @Test
    public void config001(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config001.xml");
        SimpleBean bean =(SimpleBean) context.getBean("simpleBean");
        bean.send();
        context.close();
    }

    @Test
    public void config002() throws JsonProcessingException {
        ClassPathResource res=new ClassPathResource("SSACoreTest/Test001/config001.xml");
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        SimpleBean bean = factory.getBean(SimpleBean.class);
        bean.send();

        BeanDefinition beanDefinition=factory.getBeanDefinition("simpleBean");
        System.out.println(beanDefinition.getBeanClassName());
    }

    @Test
    public void config003() throws JsonProcessingException {
        ClassPathResource res=new ClassPathResource("SSACoreTest/Test001/config001.xml");
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        BeanLifeBean bean = factory.getBean(BeanLifeBean.class);
        bean.send();
    }
}
