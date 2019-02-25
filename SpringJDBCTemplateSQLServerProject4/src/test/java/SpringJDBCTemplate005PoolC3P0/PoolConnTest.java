package SpringJDBCTemplate005PoolC3P0;

import com.mchange.v2.c3p0.impl.NewProxyConnection;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.JdbcAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PoolConnTest {

    @Test
    public void conn100() throws SQLException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig005.class);
        JdbcAccessor jdbcAccessor=annotationConfigApplicationContext.getBean(JdbcAccessor.class);
        List<String> connCodeList=new ArrayList<>();
        for(int i=0;i<150;i++){
            Connection c3p0connection1=jdbcAccessor.getDataSource().getConnection();
            Connection c3p0connection2=jdbcAccessor.getDataSource().getConnection();
            Connection c3p0connection3=jdbcAccessor.getDataSource().getConnection();
            String s1=((NewProxyConnection) c3p0connection1).toString();
            String s2=((NewProxyConnection) c3p0connection2).toString();
            String s3=((NewProxyConnection) c3p0connection3).toString();
            connCodeList.add(s1.split("wrapping")[1]);
            connCodeList.add(s2.split("wrapping")[1]);
            connCodeList.add(s3.split("wrapping")[1]);
            c3p0connection1.close();
            c3p0connection2.close();
            c3p0connection3.close();
        }
        Collections.sort(connCodeList);
        for (String connection : connCodeList) {
            System.out.println(connection);
        }
    }
}
