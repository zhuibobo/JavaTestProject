package spring013AsyncScheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhuangrb on 2017/12/11.
 */
@Configuration
@EnableScheduling
public class DIConfig013 implements SchedulingConfigurer {

    @Bean
    public ATask getATask(){
        return new ATask();
    }

    @Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
    public void Call1Task(){
        getATask().aTask("CallATask_1:");
    }

    @Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
    public void Call2Task(){
        getATask().aTask("CallATask_2:");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
