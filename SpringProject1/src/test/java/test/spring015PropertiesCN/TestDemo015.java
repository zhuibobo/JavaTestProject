package test.spring015PropertiesCN;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring015PropertiesCN.DIConfig015;
import spring015PropertiesCN.DIConfig015Encoding;
import spring015PropertiesCN.PropertiesCN;

public class TestDemo015 {

    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig015Encoding.class);
        PropertiesCN propertiesCN=annotationConfigApplicationContext.getBean(PropertiesCN.class);
        System.out.println(propertiesCN.getName());
        Assert.assertEquals("我叫中文",propertiesCN.getName());
        annotationConfigApplicationContext.close();
    }

    @Test
    public void testPrintMessage2() {
        //读取PropertiesCN.properties乱码.
        //1:进入设置,勾选setting->editor->file encodings->transparenet native-to-ascii conversion
        //2:修改properties并进行保存
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig015.class);
        PropertiesCN propertiesCN=annotationConfigApplicationContext.getBean(PropertiesCN.class);
        System.out.println(propertiesCN.getName());
        Assert.assertEquals("我叫中文1",propertiesCN.getName());
        annotationConfigApplicationContext.close();
    }
}
