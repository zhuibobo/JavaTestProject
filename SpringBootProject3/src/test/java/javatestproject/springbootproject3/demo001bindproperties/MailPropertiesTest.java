package javatestproject.springbootproject3.demo001bindproperties;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class MailPropertiesTest {
    @Test
    public void testPrintMessage() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig001.class);
        MailProperties mailProperties=annotationConfigApplicationContext.getBean(MailProperties.class);
        String name=mailProperties.getHost();
        System.out.println(name);
        annotationConfigApplicationContext.close();
        assertEquals("localhost",name);
    }
}