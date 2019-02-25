package SpringJDBCTemplate002DataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ConnAndSearch {
    private interface IQuery{
        ResultSet Query(Connection dbConn) throws SQLException;
    }

    private interface IResultSetHandler<T>{
        T ResultSetHandler(ResultSet resultSet) throws SQLException, IllegalAccessException;
    }

    private <T> T Query(DataSource dataSource,IQuery query,IResultSetHandler resultSetHandler){
        long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        T result = null;
        try {
            dbConn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement sql;
        try {
            ResultSet resultSet=query.Query(dbConn);
            result= (T) resultSetHandler.ResultSetHandler(resultSet);
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("全部运行时间:"+(endTime-startTime));
        return result;
    }

    public String QueryName(DataSource dataSource){
        String name=Query(dataSource, new IQuery() {
            @Override
            public ResultSet Query(Connection dbConn) throws SQLException {
                Statement sql = dbConn.createStatement();
                sql.execute("DELETE person");
                sql.execute("INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES('20000','zrb','男','99','445102','1982-06-12')");
                ResultSet resultSet = sql.executeQuery("select * from person where id=20000");
                return resultSet;
            }
        }, new IResultSetHandler<String>() {
            @Override
            public String ResultSetHandler(ResultSet resultSet) throws SQLException {
                if(resultSet.next()){
                    return resultSet.getString("name");
                }
                return "";
            }
        });
        return name;
        /*
        String name="";
        try {
            if(resultSet.next()){
                name=resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        /*long startTime=System.currentTimeMillis();
        Connection dbConn = null;
        String name="";
        try {
            dbConn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement sql;
        try {
            sql = dbConn.createStatement();
            sql.execute("DELETE person");
            sql.execute("INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES('20000','zrb','男','99','445102','1982-06-12')");

            ResultSet resultSet=sql.executeQuery("select * from person where id=20000");
            if(resultSet.next()){
                name=resultSet.getString("name");
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
        System.out.println("运行时间:"+(endTime-startTime));
        return name;*/
    }

    public List<Map> QueryListMap(int num, DataSource dataSource){
        List<Map> mapList;
        mapList=Query(dataSource, new IQuery() {
            @Override
            public ResultSet Query(Connection dbConn) throws SQLException {
                Statement sql = dbConn.createStatement();
                sql.execute("DELETE person");
                for (int i=0;i<num;i++) {
                    String insertSql="INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES("+i+",'zrb"+i+"','男','99','445102','1982-06-12')";
                    sql.execute(insertSql);
                }
                long startTime=System.currentTimeMillis();
                ResultSet resultSet = sql.executeQuery("select * from person");
                long endTime=System.currentTimeMillis();
                System.out.println("获取ResultSet时间:"+(endTime-startTime));
                return resultSet;
            }
        }, new IResultSetHandler<List<Map>>() {
            @Override
            public List<Map> ResultSetHandler(ResultSet resultSet) throws SQLException {
                long startTime=System.currentTimeMillis();
                List<Map> result=new ArrayList<>();
                while (resultSet.next()){
                    Map map=new HashMap();
                    map.put("id",resultSet.getObject("id"));
                    map.put("name",resultSet.getObject("name"));
                    map.put("sex",resultSet.getObject("sex"));
                    map.put("age",resultSet.getObject("age"));
                    map.put("idcard",resultSet.getObject("idcard"));
                    map.put("brithday",resultSet.getObject("brithday"));
                    result.add(map);
                }
                long endTime=System.currentTimeMillis();
                System.out.println("转换处理时间:"+(endTime-startTime));
                return result;
            }
        });
        return mapList;
    }

    public List<Person> QueryListPerson(int num, DataSource dataSource){
        List<Person> personList;
        personList=Query(dataSource, dbConn -> {
            Statement sql = dbConn.createStatement();
            sql.execute("DELETE person");
            for (int i=0;i<num;i++) {
                String insertSql="INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES("+i+",'zrb to bean"+i+"','男','99','445102','1982-06-12')";
                sql.execute(insertSql);
            }
            long startTime=System.currentTimeMillis();
            ResultSet resultSet = sql.executeQuery("select * from person");
            long endTime=System.currentTimeMillis();
            System.out.println("获取ResultSet时间:"+(endTime-startTime));
            return resultSet;
        }, (IResultSetHandler<List<Person>>) resultSet -> {
            long startTime=System.currentTimeMillis();
            List<Person> result=new ArrayList<>();
            while (resultSet.next()){
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setAge(resultSet.getInt("age"));
                person.setBrithday(resultSet.getString("brithday"));
                person.setIdcard(resultSet.getString("idcard"));
                person.setSex(resultSet.getString("sex"));
                person.setName(resultSet.getString("name"));
                result.add(person);
            }
            long endTime=System.currentTimeMillis();
            System.out.println("转换处理时间:"+(endTime-startTime));
            return result;
        });
        return personList;
    }

    public List<PersonBrithday> QueryListPersonBrithday(int num, DataSource dataSource){
        List<PersonBrithday> personList;
        personList=Query(dataSource, dbConn -> {
            Statement sql = dbConn.createStatement();
            sql.execute("DELETE person");
            for (int i=0;i<num;i++) {
                String insertSql="INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES("+i+",'zrb to bean person brithday"+i+"','男','99','445102','1982-06-12')";
                sql.execute(insertSql);
            }
            long startTime=System.currentTimeMillis();
            ResultSet resultSet = sql.executeQuery("select * from person");
            long endTime=System.currentTimeMillis();
            System.out.println("获取ResultSet时间:"+(endTime-startTime));
            return resultSet;
        }, (IResultSetHandler<List<PersonBrithday>>) resultSet -> {
            long startTime=System.currentTimeMillis();
            List<PersonBrithday> result=new ArrayList<>();
            while (resultSet.next()){
                PersonBrithday person=new PersonBrithday();
                person.setId(resultSet.getInt("id"));
                person.setAge(resultSet.getInt("age"));
                person.setBrithday(resultSet.getDate("brithday"));
                person.setIdcard(resultSet.getString("idcard"));
                person.setSex(resultSet.getString("sex"));
                person.setName(resultSet.getString("name"));
                result.add(person);
            }
            long endTime=System.currentTimeMillis();
            System.out.println("转换处理时间:"+(endTime-startTime));
            return result;
        });
        return personList;
    }

    public List<PersonBrithday> QueryListPersonBrithdayConvertByClassInfo(int num, DataSource dataSource){
        List<PersonBrithday> personList;
        personList=Query(dataSource, dbConn -> {
            Statement sql = dbConn.createStatement();
            sql.execute("DELETE person");
            for (int i=0;i<num;i++) {
                String insertSql="INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES("+i+",'QueryListPersonBrithdayConvertByClassInfo"+i+"','男','99','445102','1982-06-12')";
                sql.execute(insertSql);
            }
            long startTime=System.currentTimeMillis();
            ResultSet resultSet = sql.executeQuery("select * from person");
            long endTime=System.currentTimeMillis();
            System.out.println("获取ResultSet时间:"+(endTime-startTime));
            return resultSet;
        }, (IResultSetHandler<List<PersonBrithday>>) resultSet -> {
            long startTime=System.currentTimeMillis();
            List<PersonBrithday> result=new ArrayList<>();
            while (resultSet.next()){
                PersonBrithday personBrithday=ConvertResultSetToEntity(resultSet);
                result.add(personBrithday);
            }
            long endTime=System.currentTimeMillis();
            System.out.println("转换处理时间:"+(endTime-startTime));
            return result;
        });
        return personList;
    }

    private PersonBrithday ConvertResultSetToEntity(ResultSet resultSet) throws SQLException, IllegalAccessException {
        Class classobj=PersonBrithday.class;
        PersonBrithday personBrithday=new PersonBrithday();
        Field[] fields=personBrithday.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.getType()== String.class){
                /*try {
                    Method mehtod=personBrithday.getClass().getMethod("get"+field.getName().);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }*/
                field.set(personBrithday,resultSet.getString(field.getName()));
            }
            else if(field.getType()==int.class){
                field.setInt(personBrithday,resultSet.getInt(field.getName()));
            }
            else if(field.getType()==Date.class){
                field.set(personBrithday,resultSet.getDate(field.getName()));
            }
            System.out.println(field.getType()+":");
            System.out.println(field.getName()+",");
        }
        return personBrithday;
    }
}
