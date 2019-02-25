package test.spring009Event;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring009Event.DIConfig009;
import spring009Event.house;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/9
 * @Description:
 * @Version 1.0.0
 */
public class TestDemo009 {
    //http://www.cnblogs.com/zhangxiaoguang/p/spring-notification.html
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig009.class);
        house housea_1= (house) annotationConfigApplicationContext.getBean(house.class);
        String name=housea_1.getFamilyName();
        housea_1.getFamilyA().AddMenber();
        //System.out.println(name);
        annotationConfigApplicationContext.close();
    }
}
