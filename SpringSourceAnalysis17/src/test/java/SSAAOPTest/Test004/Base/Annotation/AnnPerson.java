package SSAAOPTest.Test004.Base.Annotation;

import SSAAOPTest.Test004.Base.IPerson;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/29
 * To change this template use File | Settings | File Templates.
 */
@Component("annPerson")
public class AnnPerson implements IPerson {
    @Override
    @Log(value = "AnnPerson")
    public String printName(String name) {
        System.out.println("print AnnPerson! "+name);
        return name;
    }
}
