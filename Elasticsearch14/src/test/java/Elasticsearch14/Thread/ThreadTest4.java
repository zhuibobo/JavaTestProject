package Elasticsearch14.Thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/29
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest4 {
    ReentrantLock reentrantLock=new ReentrantLock();

    ArrayList list=new ArrayList();
    //Vector list=new Vector();

    class userTask1 implements Runnable{
        @Override
        public void run() {
            //reentrantLock.lock();
            //System.out.println(Thread.currentThread().getId());
            reentrantLock.lock();
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
            reentrantLock.unlock();
            //reentrantLock.unlock();
        }
    }

    @Test
    public void runThread() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread=new Thread(new userTask1());
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }
}
