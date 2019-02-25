package spring012SimpleScheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by zhuangrb on 2017/12/11.
 */
@Configuration
@EnableScheduling
public class DIConfig012 {

    @Bean
    public ATask getATask(){
        return new ATask();
    }

    //spring的定时任务默认是单线程，多个任务执行起来时间会有问题任务之间会相互延迟.

    @Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
    public void Call1Task(){
        getATask().aTask("CallATask_1:");
    }

    @Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
    public void Call2Task(){
        getATask().aTask("CallATask_2:");
    }
}
