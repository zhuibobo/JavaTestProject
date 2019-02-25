package spring005PrimaryBean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
public class DIConfig005 {
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

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public house getHouseB(){
        house house1=new houseB();
        house1.setFamilyA(getfamily());
        return house1;
    }
}
