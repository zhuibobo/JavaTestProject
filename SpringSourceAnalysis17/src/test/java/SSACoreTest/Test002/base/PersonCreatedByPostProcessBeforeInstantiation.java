package SSACoreTest.Test002.base;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/22
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PersonCreatedByPostProcessBeforeInstantiation {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello(){
        System.out.println("hello:"+getName());
    }
}
