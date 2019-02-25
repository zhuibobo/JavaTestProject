package spring008Aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@PropertySource(value = "classpath:demo7.properties", encoding = "utf-8")
public class DIConfig008 {

    @Bean
    public family getfamily(){
        return new family();
    }

    @Bean(name = "HouseABean")
    public house getHouseA(){
        house house1=new houseA();
        house1.setFamilyA(getfamily());
        return house1;
    }
}
