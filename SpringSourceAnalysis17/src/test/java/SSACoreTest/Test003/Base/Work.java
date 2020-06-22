package SSACoreTest.Test003.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Work {

    @Autowired
    @Qualifier("meme")
    @Qualifier1
    IPerson person;

    public void work(){
        System.out.println(person.getName());
    }
}
