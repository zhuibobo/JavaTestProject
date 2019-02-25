package SpringJDBCTemplate002DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/21
 * @Description:
 * @Version 1.0.0
 */
public class ConnAndInsertTest {

    @Test
    public void insertPersonNormal() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndInsert connAndInsert=new ConnAndInsert();
        connAndInsert.InsertPersonNormal(100,true,dataSource);
    }
}