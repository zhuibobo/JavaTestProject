package SSACoreTest.Test002.base;


import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out
                .println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
                                                 String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法"+beanName);
        if(beanName.equals("personCreatedByPostProcessBeforeInstantiation")){
            PersonCreatedByPostProcessBeforeInstantiation personCreatedByPostProcessBeforeInstantiation=new PersonCreatedByPostProcessBeforeInstantiation();
            return personCreatedByPostProcessBeforeInstantiation;
        }
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法"+beanName);
        if(beanName.equals("personCreatedByPostProcessBeforeInstantiation")){
            ((PersonCreatedByPostProcessBeforeInstantiation)bean).setName("i'm personCreatedByPostProcessBeforeInstantiation");
        }
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法"+beanName);
        return pvs;
    }
}