package spring009Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@PropertySource(value = "classpath:demo7.properties", encoding = "utf-8")
public class DIConfig009 {
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public family getfamily(){
        family family1=new family();
        family1.applicationContext=applicationContext;
        return family1;
    }

    @Bean(name = "HouseABean")
    public house getHouseA(){
        house house1=new houseA();
        house1.setFamilyA(getfamily());
        return house1;
    }
}
