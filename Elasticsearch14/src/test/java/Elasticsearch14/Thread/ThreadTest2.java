package Elasticsearch14.Thread;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/29
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest2 {
    Object sysObj=new Object();

    class userTask1 implements Runnable{
        @Override
        public void run() {
            synchronized (sysObj) {
                System.out.println("im thread " + Thread.currentThread().getId()+" "+Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void runThread() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Thread thread1=new Thread(new userTask1(),"alex-thread-"+i);
            thread1.start();
        }
        Thread.sleep(5000);
    }

    @Test
    public void runThreadGroup() throws InterruptedException {
        ThreadGroup threadGroup=new ThreadGroup("my-thread-group");
        for (int i = 0; i < 50; i++) {
            Thread thread1=new Thread(threadGroup,new userTask1(),"alex-thread-"+i);
            thread1.start();
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println(threadGroup.activeCount());
        }
        Thread.sleep(5000);
    }
}
