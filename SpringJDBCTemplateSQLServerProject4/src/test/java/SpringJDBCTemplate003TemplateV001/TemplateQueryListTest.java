package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TemplateQueryListTest {

    @Test
    public void queryForListSQL() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryList templateQueryList=new TemplateQueryList();
        List<Map<String, Object>> personList=templateQueryList.QueryForListSQL(1000,jdbcOperations);
        Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",personList.get(0).get("name"));
    }

    @Test
    public void queryForListSQLClassT() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryList templateQueryList=new TemplateQueryList();

        List<Integer> personIdList=templateQueryList.QueryForListSQLClassT(1000,jdbcOperations);
        Assert.assertEquals(1000,personIdList.size());
        System.out.println(personIdList);
        //Assert.assertEquals("InsertPersonForQuery",personList.get(0).get("name"));
    }

    @Test
    public void queryForListParasSQLClassT() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryList templateQueryList=new TemplateQueryList();

        List<Integer> personIdList=templateQueryList.QueryForListParasSQLClassT(1000,jdbcOperations);
        Assert.assertEquals(999,personIdList.size());
        System.out.println(personIdList);
    }
}