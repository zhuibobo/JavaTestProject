package Elasticsearch14.Thread;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/28
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest {

    @Test
    public void Test1() throws InterruptedException {
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println("111111111111111111111111111111");
                try {
                    System.out.println(Thread.currentThread().getId());
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        System.out.println(Thread.currentThread().getId());
        Thread.currentThread().sleep(1000);
        thread.interrupt();
    }
}
