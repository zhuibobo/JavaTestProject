package spring003Aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by zhuangrb on 2017/12/5.
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class DIConfig003 {

    @Bean
    public spring003Aop.family getfamily(){
        return new family();
    }

    @Bean
    public housebyAnnotation getHouse(){
        housebyAnnotation house1=new housebyAnnotation();
        house1.familyA=getfamily();
        return house1;
    }

    @Bean
    public housebyMethodName getHouseMethod(){
        housebyMethodName house1=new housebyMethodName();
        house1.familyA=getfamily();
        return house1;
    }
}
