package SpringJDBCTemplate004PoolDBCP;

import Base.ListSort;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.JdbcAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoolConnTest {

    @Test
    public void conn100() throws SQLException {
        //http://www.cnblogs.com/hehaiyang/p/4275228.html
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig004.class);
        JdbcAccessor jdbcAccessor=annotationConfigApplicationContext.getBean(JdbcAccessor.class);
        List<Integer> connCodeList=new ArrayList<>();
        for(int i=0;i<15;i++){
            Connection connection1=jdbcAccessor.getDataSource().getConnection();
            Connection connection2=jdbcAccessor.getDataSource().getConnection();
            Connection connection3=jdbcAccessor.getDataSource().getConnection();
            connCodeList.add(connection1.hashCode());
            connCodeList.add(connection2.hashCode());
            connCodeList.add(connection3.hashCode());
            connection1.close();
            connection2.close();
            connection3.close();
        }
        ListSort.SortInt(connCodeList);
        for (int connection : connCodeList) {
            System.out.println(connection);
        }

    }
}
