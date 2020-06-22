package SSAAOPTest.Test004.Base.Method;

import SSAAOPTest.Test004.Base.IPerson;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Component("cnPerson")
public class CNPerson implements IPerson {
    @Override
    public String printName(String name) {
        System.out.println("print CNPerson! "+name);
        return name;
    }
}
