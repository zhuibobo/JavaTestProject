package SSAAOPTest.Test004.Base;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Person1 implements IPerson {
    @Override
    public String printName(String name) {
        System.out.println("Person1 Alex");
        return "Alex";
    }
}
