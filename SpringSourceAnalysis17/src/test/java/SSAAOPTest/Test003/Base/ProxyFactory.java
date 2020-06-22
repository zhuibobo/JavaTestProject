package SSAAOPTest.Test003.Base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */

@Component
public class ProxyFactory<T> implements ApplicationContextAware {

    ApplicationContext applicationContext;

    //维护目标对象
    private Object target;

    //维护关键点代码的类
    @Autowired
    private AOP aop;

    private Object porxyObj;

    /*@Autowired
    private T target_;*/


    public T createProxyInstance(Class<T> tClass) {

        //T t=new T();
        //目标对象和关键点代码的类都是通过外界传递进来
        target = applicationContext.getBean(tClass);
        //aop = aop_

        porxyObj=Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aop.begin();
                        System.out.println("----------------------------------");
                        Object returnValue = method.invoke(target, args);
                        System.out.println("----------------------------------");
                        aop.close();
                        return returnValue;
                    }
                }
        );

        return (T)porxyObj;
    }

    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException {
        applicationContext=_applicationContext;
    }

}
