package SpringJDBCTemplate003TemplateV001;

import Base.ListSort;
import SpringJDBCTemplate005PoolC3P0.PoolConnTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.JdbcAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TemplateConnTest {

    @Test
    public void conn() throws SQLException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcAccessor jdbcAccessor=annotationConfigApplicationContext.getBean(JdbcAccessor.class);
        Connection connection1=jdbcAccessor.getDataSource().getConnection();
        Connection connection2=jdbcAccessor.getDataSource().getConnection();
        System.out.println(connection1.hashCode());
        System.out.println(connection2.hashCode());
        Assert.assertNotEquals(connection1,connection2);
    }

    @Test
    public void conn100() throws SQLException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig003.class);
        JdbcAccessor jdbcAccessor=annotationConfigApplicationContext.getBean(JdbcAccessor.class);
        List<Integer> connCodeList=new ArrayList<>();
        for(int i=0;i<100;i++){
            connCodeList.add(jdbcAccessor.getDataSource().getConnection().hashCode());
        }
        ListSort.SortInt(connCodeList);
        for (int connection : connCodeList) {
            System.out.println(connection);
        }

    }
}
