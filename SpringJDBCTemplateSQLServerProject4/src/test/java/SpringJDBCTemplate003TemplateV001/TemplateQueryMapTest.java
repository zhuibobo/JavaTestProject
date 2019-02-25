package SpringJDBCTemplate003TemplateV001;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/27
 * @Description:
 * @Version 1.0.0
 */
public class TemplateQueryMapTest {

    @Test
    public void queryForMap() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcOperations jdbcOperations=annotationConfigApplicationContext.getBean(JdbcOperations.class);
        TemplateQueryMap templateQueryMap=new TemplateQueryMap();
        Map<String, Object> map=templateQueryMap.QueryForMap(1000,jdbcOperations);
        //Assert.assertEquals(1000,personList.size());
        Assert.assertEquals("InsertPersonForQuery",map.get("name"));
        Assert.assertEquals(0,map.get("id"));
    }
}