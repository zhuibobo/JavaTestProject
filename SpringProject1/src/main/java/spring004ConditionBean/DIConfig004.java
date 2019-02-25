package spring004ConditionBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
public class DIConfig004 {
    @Bean
    public family getfamily(){
        return new family();
    }

    @Bean
    @Conditional({ConditionalDemo4.class})
    public house getHouseA(){
        house house1=new houseA();
        house1.setFamilyA(getfamily());
        return house1;
    }

    @Bean
    public house getHouseB(){
        house house1=new houseB();
        house1.setFamilyA(getfamily());
        return house1;
    }
}
