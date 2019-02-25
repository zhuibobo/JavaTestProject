package test.spring011AopImplInteface;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring011AopImplInteface.DIConfig011;
import spring011AopImplInteface.house;
import spring011AopImplInteface.inewhouse;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/10
 * @Description:
 * @Version 1.0.0
 */
public class TestDemo011 {

    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig011.class);
        inewhouse housea_1= (inewhouse) annotationConfigApplicationContext.getBean("HouseABean");
        String name= housea_1.getNewFamilyName((house) housea_1);
        System.out.println(name);
        System.out.println(housea_1.getClass().getName());
        annotationConfigApplicationContext.close();
        annotationConfigApplicationContext.close();
    }
}
