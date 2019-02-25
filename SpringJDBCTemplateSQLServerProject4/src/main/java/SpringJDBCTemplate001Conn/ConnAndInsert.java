package SpringJDBCTemplate001Conn;

import java.sql.*;

public class ConnAndInsert {
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4";
    String userName = "sa";   //默认用户名
    String userPwd = "sql";   //密码
    public long InsertPersonNormal(int num,boolean autoCommit){
        long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        Statement sql;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
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

    public long InsertPersonPrepared(int num,boolean autoCommit){
        long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        Statement sql;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            sql = dbConn.createStatement();
            sql.execute("DELETE person");

            String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
            /**执行SQL预编译**/
            preparedStatement = dbConn.prepareStatement(sqlString);
            if(!autoCommit) {
                dbConn.setAutoCommit(false);
            }
            for (int i=0;i<num;i++) {
                preparedStatement.setObject(1,i);
                preparedStatement.setObject(2,"zrb");
                preparedStatement.setObject(3,"男");
                preparedStatement.setObject(4,"98");
                preparedStatement.setObject(5,"445102");
                preparedStatement.setObject(6,"1982-06-12");
                preparedStatement.execute();
            }
            if(!autoCommit){
                dbConn.commit();
            }
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        return (endTime-startTime);
    }

    public long InsertPersonBatch(int num){
        long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        Statement sql;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            sql = dbConn.createStatement();
            sql.execute("DELETE person");

            String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
            /**执行SQL预编译**/
            preparedStatement = dbConn.prepareStatement(sqlString);
            dbConn.setAutoCommit(false);
            for (int i=0;i<num;i++) {
                preparedStatement.setObject(1,i);
                preparedStatement.setObject(2,"zrb");
                preparedStatement.setObject(3,"男");
                preparedStatement.setObject(4,"98");
                preparedStatement.setObject(5,"445102");
                preparedStatement.setObject(6,"1982-06-12");
                preparedStatement.addBatch();
            }
            int[] arr = preparedStatement.executeBatch();
            int affectRowCount = -1;
            affectRowCount  = arr.length;
            System.out.println("成功了插入了" + affectRowCount + "行");
            dbConn.commit();
            dbConn.close();
        } catch (SQLException e) {
            if (dbConn != null) {
                try {
                    dbConn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        return (endTime-startTime);
    }
}
