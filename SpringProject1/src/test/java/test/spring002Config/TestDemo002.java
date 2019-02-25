package test.spring002Config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring002Config.DIConfig002;
import spring002Config.house;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo002 {

    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        house housea=annotationConfigApplicationContext.getBean(house.class);
        String name=housea.getFamilyName();
        System.out.println(name+"demo2");
        System.out.println(housea.getClass().getName());
        annotationConfigApplicationContext.close();
        assertEquals("alex4D",name);
    }

}
