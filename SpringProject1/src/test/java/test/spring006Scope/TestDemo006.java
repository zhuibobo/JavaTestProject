package test.spring006Scope;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring006Scope.DIConfig006;
import spring006Scope.house;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo006 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig006.class);
        house housea_1= (house) annotationConfigApplicationContext.getBean("HouseABean");
        house housea_2= (house) annotationConfigApplicationContext.getBean("HouseABean");
        if(housea_1.equals(housea_2)){
            System.out.println("housea_1==housea_2");
        }
        house houseb_1= (house) annotationConfigApplicationContext.getBean("HouseBBean");
        house houseb_2= (house) annotationConfigApplicationContext.getBean("HouseBBean");
        if(!houseb_1.equals(houseb_2)){
            System.out.println("housea_1!==housea_2");
        }
        annotationConfigApplicationContext.close();
    }
}
