package Elasticsearch14.Thread;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/29
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest3 {
    Object sysObj=new Object();

    class userTask1 implements Runnable{
        @Override
        public void run() {
            synchronized (sysObj) {
                System.out.println("im userTask1 " + Thread.currentThread().getId());
                try {
                    System.out.println("im userTask1 " + Thread.currentThread().getId()+" i wait");
                    sysObj.wait();
                    System.out.println("im userTask1 " + Thread.currentThread().getId()+" i go");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class userTask2 implements Runnable{
        @Override
        public void run() {
            synchronized (sysObj) {
                System.out.println("im userTask2 " + Thread.currentThread().getId());
                try {
                    Thread.sleep(4000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("im userTask2 notify");
                sysObj.notify();
            }
        }
    }

    @Test
    public void runThread() throws InterruptedException {
        Thread thread1=new Thread(new userTask1());
        thread1.start();
        Thread thread2=new Thread(new userTask2());
        thread2.start();
        Thread.sleep(5000);
    }
}
