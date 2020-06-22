package SSACoreTest.Test003.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/28
 * To change this template use File | Settings | File Templates.
 */
@Component("person2")
@PropertySource("classpath:/SSACoreTest/Test003/config2.properties")
@Primary
@Qualifier("meme")
@Qualifier1
public class Person2 implements IPerson {
    @Autowired
    Environment env;


    @Override
    public String getName() {
        return "i'm "+env.getProperty("name1");
    }
}
