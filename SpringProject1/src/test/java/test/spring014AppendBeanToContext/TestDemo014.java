package test.spring014AppendBeanToContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring014AppendBeanToContext.AppendBean;
import spring014AppendBeanToContext.DIConfig014;
import spring014AppendBeanToContext.house;

public class TestDemo014 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig014.class);

        Assert.assertEquals("alex4D",annotationConfigApplicationContext.getBean(house.class).getFamilyName());

        try {
            annotationConfigApplicationContext.getBean(AppendBean.class);
        }
        catch (BeansException ex){
            System.out.println(ex.getMessage());
            Assert.assertEquals("No qualifying bean of type 'spring014AppendBeanToContext.AppendBean' available",ex.getMessage());
        }
        annotationConfigApplicationContext.register(AppendBean.class);
        AppendBean appendBean=annotationConfigApplicationContext.getBean(AppendBean.class);
        //annotationConfigApplicationContext.refresh();
        System.out.println(appendBean.getMyName());
        Assert.assertEquals("AppendBean",appendBean.getMyName());
        annotationConfigApplicationContext.close();
    }
}
