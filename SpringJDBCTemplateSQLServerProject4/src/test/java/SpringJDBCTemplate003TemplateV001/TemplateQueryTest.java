package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

import static org.junit.Assert.*;

public class TemplateQueryTest {

    @Test
    public void queryPersonNormal() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByRowMapper(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(0).getName());
    }

    @Test
    public void queryPersonByPreparedStatementRowMapper() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByPreparedStatementRowMapper(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(0).getName());
    }

    @Test
    public void queryPersonByPreparedStatementSetterRowMapper() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByPreparedStatementSetterRowMapper(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(0).getName());
    }

    @Test
    public void queryPersonByParasRowMapper() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByParasRowMapper(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(999).getName());
    }

    @Test
    public void queryPersonByResultSetExtractor() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByResultSetExtractor(1000,jdbcOperations);
        System.out.println("查询结果数据:"+personList.size());
        Assert.assertEquals(1000-51,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(0).getName());
    }

    @Test
    public void queryPersonByRowCallbackHandler() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQuery templateQuery=new TemplateQuery();
        List<Person> personList=templateQuery.QueryPersonByRowCallbackHandler(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(999).getName());
    }
}