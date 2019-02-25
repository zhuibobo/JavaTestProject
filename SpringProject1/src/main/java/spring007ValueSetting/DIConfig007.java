package spring007ValueSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@PropertySource(value = "classpath:demo7.properties", encoding = "utf-8")
//允许支持多个properties， 如果是相同的key，则最后一个起作用
public class DIConfig007 {

    @Autowired
    private Environment env;

    @Value("${address1}")
    private String address1;

    @Value("https://www.baidu.com/")
    private Resource baidu;

    @Value("${p.address3:奇奇怪怪}")
    private String bindPropAndDefaultValue;

    @Value("${p.address4:奇奇怪怪}")
    private String bindPropAndDefaultValueD;

    @Bean
    public family getfamily(){
        return new family();
    }

    @Bean(name = "HouseABean")
    public house getHouseA(){
        house house1=new houseA();
        house1.setAddress(address1);
        house1.setAddress2(env.getProperty("address2"));
        house1.setAddress3(bindPropAndDefaultValue);
        house1.setAddress4(bindPropAndDefaultValueD);
        house1.setBaiduResource(baidu);
        house1.setFamilyA(getfamily());
        return house1;
    }
}
