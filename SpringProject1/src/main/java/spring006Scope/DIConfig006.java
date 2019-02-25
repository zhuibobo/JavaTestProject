package spring006Scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
public class DIConfig006 {
    //http://www.logicbig.com/tutorials/spring-framework/spring-core/scoped-proxy/
    //https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html#beans-factory-scopes-prototype

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

    @Bean(name = "HouseBBean")
    @Scope("prototype")
    public house getHouseB(){
        house house1=new houseB();
        house1.setFamilyA(getfamily());
        return house1;
    }
}
