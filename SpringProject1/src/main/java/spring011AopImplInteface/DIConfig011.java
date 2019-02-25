package spring011AopImplInteface;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by zhuangrb on 2017/12/5.
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class DIConfig011 {

    @Bean
    public AOPAspect getAOPAspect(){
        return new AOPAspect();
    }

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
