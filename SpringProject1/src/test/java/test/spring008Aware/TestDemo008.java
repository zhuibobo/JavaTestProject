package test.spring008Aware;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring008Aware.DIConfig008;
import spring008Aware.house;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/9
 * @Description:
 * @Version 1.0.0
 */
public class TestDemo008 {
    @Test
    public void testPrintMessage() {
        //http://blog.csdn.net/soonfly/article/details/70017238
        //http://www.jianshu.com/p/73981795cfa4
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig008.class);
        house housea_1= (house) annotationConfigApplicationContext.getBean(house.class);
        String name=housea_1.getFamilyName();
        System.out.println(name);
        annotationConfigApplicationContext.close();
    }
}
