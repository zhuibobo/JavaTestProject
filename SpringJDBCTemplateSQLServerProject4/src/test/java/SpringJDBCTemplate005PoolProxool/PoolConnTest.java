package SpringJDBCTemplate005PoolProxool;

import SpringJDBCTemplate005PoolC3P0.DIConfig005;
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
        //Error http://fireinwind.iteye.com/blog/1570165
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig006.class);
        JdbcAccessor jdbcAccessor=annotationConfigApplicationContext.getBean(JdbcAccessor.class);
        List<String> connCodeList=new ArrayList<>();
        for(int i=0;i<150;i++){
            Connection proxoolDataSource1=jdbcAccessor.getDataSource().getConnection();
            Connection proxoolDataSource2=jdbcAccessor.getDataSource().getConnection();
            Connection proxoolDataSource3=jdbcAccessor.getDataSource().getConnection();
            String s1=((NewProxyConnection) proxoolDataSource1).toString();
            String s2=((NewProxyConnection) proxoolDataSource2).toString();
            String s3=((NewProxyConnection) proxoolDataSource3).toString();
            connCodeList.add(s1.split("wrapping")[1]);
            connCodeList.add(s2.split("wrapping")[1]);
            connCodeList.add(s3.split("wrapping")[1]);
            proxoolDataSource1.close();
            proxoolDataSource2.close();
            proxoolDataSource3.close();
        }
        Collections.sort(connCodeList);
        for (String connection : connCodeList) {
            System.out.println(connection);
        }
    }
}
