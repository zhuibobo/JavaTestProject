package spring002Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@EnableAspectJAutoProxy
public class DIConfig002 {
    @Bean
    public family getfamily(){
        return new family();
    }

    @Bean
    public house getHouse(){
        house house1=new house();
        house1.familyA=getfamily();
        return house1;
    }
}
