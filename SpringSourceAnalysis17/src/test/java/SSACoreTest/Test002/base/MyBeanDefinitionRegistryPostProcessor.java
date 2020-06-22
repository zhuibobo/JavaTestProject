package SSACoreTest.Test002.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/21
 * To change this template use File | Settings | File Templates.
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
        //向Spring容器中注册OrderService
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(OrderService.class)
                //这里的属性名是根据setter方法
                //.addPropertyReference("dao", "orderDao")
                .setInitMethodName("init")
                .setScope(BeanDefinition.SCOPE_SINGLETON)
                .getBeanDefinition();

        registry.registerBeanDefinition("orderService", beanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
        // 在这里修改orderService bean的scope为PROTOTYPE
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("orderService");
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }
}
