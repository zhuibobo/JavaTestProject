package SSAAOPTest.Test002.Base;

import SSAAOPTest.Test001.Base.IPerson;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/23
 * To change this template use File | Settings | File Templates.
 */
public class Person implements IPerson {
    @Override
    public void printName(String name) {
        System.out.println("hello "+name);
    }
}
