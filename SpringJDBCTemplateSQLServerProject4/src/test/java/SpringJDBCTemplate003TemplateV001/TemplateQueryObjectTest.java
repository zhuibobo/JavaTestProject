package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/27
 * @Description:
 * @Version 1.0.0
 */
public class TemplateQueryObjectTest {

    @Test
    public void queryForObject() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryObject templateQueryObject=new TemplateQueryObject();
        int personid=templateQueryObject.QueryForObject(1000,jdbcOperations);
        Assert.assertEquals(0,personid);
    }

    @Test
    public void queryForObjectRowMapper() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryObject templateQueryObject=new TemplateQueryObject();
        Person person=templateQueryObject.QueryForObjectRowMapper(1000,jdbcOperations);
        Assert.assertEquals(0,person.getId());
    }
}