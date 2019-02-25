package test.spring001Annotation;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring001Annotation.DIConfig001;
import spring001Annotation.house;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo001 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig001.class);
        house housea=annotationConfigApplicationContext.getBean(house.class);
        String name=housea.getFamilyName();
        System.out.println(name+"demo1");
        System.out.println(housea.getClass().getName());
        annotationConfigApplicationContext.close();
        assertEquals("alex4D",name);
    }
}
