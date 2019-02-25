package test.spring003Aop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring003Aop.DIConfig003;
import spring003Aop.housebyAnnotation;
import spring003Aop.housebyMethodName;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo003 {

    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        housebyAnnotation housebyAnnotation1=annotationConfigApplicationContext.getBean(housebyAnnotation.class);

        System.out.println("housebyAnnotation--begin");
        String name=housebyAnnotation1.getFamilyName();
        System.out.println(name);
        System.out.println("housebyAnnotation--end");
        System.out.println("-----------------------------------------------------------------------------------");
        housebyMethodName housebyMethodName1=annotationConfigApplicationContext.getBean(housebyMethodName.class);
        System.out.println("housebyMethodName1--begin");
        name=housebyMethodName1.getFamilyName();
        System.out.println(name);
        System.out.println("housebyMethodName1--end");
        annotationConfigApplicationContext.close();
    }
}
