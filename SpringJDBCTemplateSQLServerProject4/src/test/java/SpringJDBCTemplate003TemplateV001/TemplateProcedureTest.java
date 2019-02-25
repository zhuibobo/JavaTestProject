package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TemplateProcedureTest {

    @Test
    public void callSPInsertPersonNotOut() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        Person person=templateProcedure.CallSPInsertPersonNotOut(jdbcOperations);
        Assert.assertEquals(1,person.getId());
    }

    @Test
    public void callSPInsertPersonNotOutByNum() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        int personNum=templateProcedure.CallSPInsertPersonNotOutByNum(1000,jdbcOperations);
        Assert.assertEquals(1000,personNum);
    }

    @Test
    public void callSPInsertPersonParamsOut() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        Map<String,String> map=templateProcedure.CallSPInsertPersonParamsOut(jdbcOperations);
        Assert.assertEquals("445102",map.get("outidcard"));
        Assert.assertEquals("CallSPInsertPersonParamsOut",map.get("outname"));
    }

    @Test
    public void callSPInsertPersonParamsCursor() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        List<Person> personList=templateProcedure.CallSPInsertPersonParamsCursor(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
    }

    @Test
    public void callSPInsertPersonSelect() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        List<Person> personList=templateProcedure.CallSPInsertPersonSelect(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
    }

    @Test
    public void callSPInsertPersonSelect2Result() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateProcedure templateProcedure=new TemplateProcedure();
        List<PersonNewC> personList=templateProcedure.CallSPInsertPersonSelect2Result(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("hellow",personList.get(0).getNewc());
    }
}