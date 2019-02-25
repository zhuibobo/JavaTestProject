package test.spring012SimpleScheduled;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring012SimpleScheduled.DIConfig012;

/**
 * Created by zhuangrb on 2017/12/11.
 */
public class TestDemo012 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig012.class);
        System.out.println("spring012Quartz");
        try {
            Thread.sleep(1000*10*3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        annotationConfigApplicationContext.close();
    }
}
