package SSACoreTest.Test003.Base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/5/28
 * To change this template use File | Settings | File Templates.
 */
@Component("person3")
@PropertySource(value = "classpath:SSACoreTest/Test003/config5.yml", factory = YamlPropertySourceFactory.class)
public class Person3 implements IPerson {

    @Value("${config3Name}")
    String name;

    @Value("${config4.name}")
    String name1;

    @Value("${config5.name}")
    String name2;

    @Value("${age}")
    String age;

    @Override
    public String getName() {
        return "i'm Person3 "+name+" age "+age+" "+name1+" "+name2;
    }
}
