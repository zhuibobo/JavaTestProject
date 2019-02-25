package SpringJDBCTemplate002DataSource;

import javax.sql.DataSource;
import java.sql.*;

public class ConnAndInsert {

    public long InsertPersonNormal(int num, boolean autoCommit, DataSource dataSource){
        long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        try {
            dbConn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement sql;
        try {
            sql = dbConn.createStatement();
            sql.execute("DELETE person");

            if(!autoCommit) {
                dbConn.setAutoCommit(false);
            }
            for (int i=0;i<num;i++) {
                String insertSql="INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES("+i+",'zrb','男','99','445102','1982-06-12')";
                sql.execute(insertSql);
            }
            if(!autoCommit){
                dbConn.commit();
            }
            System.out.println("成功了插入了" + num + "行");
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        return (endTime-startTime);
    }
}
