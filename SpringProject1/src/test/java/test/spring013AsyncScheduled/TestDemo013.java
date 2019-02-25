package test.spring013AsyncScheduled;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring013AsyncScheduled.DIConfig013;

/**
 * Created by zhuangrb on 2017/12/11.
 */
public class TestDemo013 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig013.class);
        System.out.println("spring013AsyncScheduled");
        try {
            Thread.sleep(1000*10*3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        annotationConfigApplicationContext.close();
    }
}
