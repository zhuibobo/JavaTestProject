package spring014AppendBeanToContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@ComponentScan
public class DIConfig014 {
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
