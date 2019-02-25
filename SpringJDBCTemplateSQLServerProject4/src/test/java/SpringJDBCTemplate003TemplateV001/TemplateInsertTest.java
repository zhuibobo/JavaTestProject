package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.ArrayList;
import java.util.List;

public class TemplateInsertTest {

    @Test
    public void insertPersonByExecute() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        int count1000=connAndInsert.InsertPersonByExecuteString(1000,jdbcOperations);
        Assert.assertEquals(1000,count1000);
    }

    @Test
    public void insertPersonByExecuteStatementCallback() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        int count100=connAndInsert.InsertPersonByExecuteStatementCallback(100,jdbcOperations);
        Assert.assertEquals(100,count100);
    }
    
    @Test
    public void insertPersonByConnectionCallback() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        int p1010=connAndInsert.InsertPersonByExecuteConnectionCallback(1000,jdbcOperations);
        Assert.assertEquals(1000,p1010);
    }


    @Test
    public void insertPersonByExecutePreparedStatementCallback() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonByExecutePreparedStatementCallback(88,jdbcOperations);
        Assert.assertEquals(88,p1010);
    }

    @Test
    public void insertPersonParams() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonParams(jdbcOperations);
        Assert.assertEquals(1,p1010);
    }

    @Test
    public void insertPersonPreparedStatement() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonPreparedStatement(jdbcOperations);
        Assert.assertEquals(1,p1010);
    }

    @Test
    public void insertPersonGeneratedKeyHolder() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonGeneratedKeyHolder(jdbcOperations);
        Assert.assertEquals(1,p1010);
    }

    @Test
    public void insertPersonStringBatch() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonStringBatch(10000,jdbcOperations);
        Assert.assertEquals(10000,p1010);
    }

    @Test
    public void insertPersonParasBatch() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonParasBatch(10000,jdbcOperations);
        Assert.assertEquals(10000,p1010);
    }

    @Test
    public void insertPersonPreparedStatementBatch() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        long p1010=connAndInsert.InsertPersonPreparedStatementBatch(10000,jdbcOperations);
        Assert.assertEquals(10000,p1010);
    }

    @Test
    public void insertPersonPreparedStatementTBatch() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateInsert connAndInsert=new TemplateInsert();
        List<Person> personList=new ArrayList<>();
        for(int i=0;i<10000;i++){
            Person person=new Person();
            person.setId(i);
            person.setName("insertPersonPreparedStatementTBatch"+i);
            person.setAge(99);
            person.setIdcard("445102");
            person.setBrithday("1982-06-12");
            person.setSex("男");
            personList.add(person);
        }
        long p1010=connAndInsert.InsertPersonPreparedStatementTBatch(personList,jdbcOperations);
        Assert.assertEquals(10000,p1010);
    }
}