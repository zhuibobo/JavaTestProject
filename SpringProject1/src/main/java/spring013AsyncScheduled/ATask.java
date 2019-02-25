package spring013AsyncScheduled;

import org.joda.time.DateTime;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhuangrb on 2017/12/11.
 */
public class ATask {

    public void aTask(String name){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(name+sdf.format(DateTime.now().toDate())+"*********A任务每2秒执行一次进入测试");
    }
}
