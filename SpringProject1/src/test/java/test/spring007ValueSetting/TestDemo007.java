package test.spring007ValueSetting;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring007ValueSetting.DIConfig007;
import spring007ValueSetting.house;

import java.io.IOException;

/**
 * Created by zhuangrb on 2017/12/8.
 */
public class TestDemo007 {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig007.class);
        house housea_1= (house) annotationConfigApplicationContext.getBean(house.class);
        System.out.println("我是中文");
        System.out.println(housea_1.getAddress());
        Assert.assertEquals("我是谁?谁是我address1",housea_1.getAddress());
        System.out.println(housea_1.getAddress2());
        Assert.assertEquals("我是谁?谁是我address2",housea_1.getAddress2());
        System.out.println(housea_1.getAddress3());
        Assert.assertEquals("我是谁?谁是我address3",housea_1.getAddress3());
        System.out.println(housea_1.getAddress4());
        Assert.assertEquals("奇奇怪怪",housea_1.getAddress4());
        try {
            System.out.println(IOUtils.toString(housea_1.getBaiduResource().getInputStream(),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        annotationConfigApplicationContext.close();
    }
}
