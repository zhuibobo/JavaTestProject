package test.spring004ConditionBean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring004ConditionBean.DIConfig004;
import spring004ConditionBean.house;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo004 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig004.class);
        house housea=annotationConfigApplicationContext.getBean(house.class);
        String name=housea.getFamilyName();
        System.out.println(name+"demo4");
        annotationConfigApplicationContext.close();
    }
}
