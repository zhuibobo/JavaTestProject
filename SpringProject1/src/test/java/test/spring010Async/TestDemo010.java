package test.spring010Async;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring010Async.DIConfig010;
import spring010Async.house;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/9
 * @Description:
 * @Version 1.0.0
 */
public class TestDemo010 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig010.class);
        house housea_1= (house) annotationConfigApplicationContext.getBean(house.class);
        for (int i = 0; i < 200; i++) {
            housea_1.getFamilyName(i);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread sleep");
        annotationConfigApplicationContext.close();
    }
}
