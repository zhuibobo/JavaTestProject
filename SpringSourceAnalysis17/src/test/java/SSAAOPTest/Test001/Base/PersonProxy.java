package SSAAOPTest.Test001.Base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
public class PersonProxy {
    IPerson person = new Person();


    //返回代理对象
    public IPerson getProxy() {

        /**
         * 参数一：代理类的类加载器
         * 参数二：被代理对象的接口
         * 参数三：InvocationHandler实现类
         */
        Object proxyObj=Proxy.newProxyInstance(PersonProxy.class.getClassLoader(), Person.class.getInterfaces(), new InvocationHandler() {

            /**
             * proxy : 把代理对象自己传递进来
             * method：把代理对象当前调用的方法传递进来
             * args:把方法参数传递进来
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("printName")) {

                    System.out.println("---------------------------------------------------");
                    method.invoke(person, args);
                    System.out.println("---------------------------------------------------");
                }
                return null;
            }
        });
        return (IPerson)proxyObj;
    }
}
