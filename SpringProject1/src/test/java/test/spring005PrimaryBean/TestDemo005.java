package test.spring005PrimaryBean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring005PrimaryBean.DIConfig005;
import spring005PrimaryBean.house;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo005 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig005.class);
        house housea=annotationConfigApplicationContext.getBean(house.class);
        //house housea= (house) annotationConfigApplicationContext.getBean("HouseABean");
        String name=housea.getFamilyName();
        System.out.println(name+"demo5");
        annotationConfigApplicationContext.close();
    }
}
