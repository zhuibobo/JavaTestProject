package Elasticsearch14;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
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

    public List<LinkedHashMap<String,Object>> QueryTopListMap(int topNum,DataSource dataSource){
        List<LinkedHashMap<String,Object>> mapList;
        mapList=Query(dataSource, new IQuery() {
            @Override
            public ResultSet Query(Connection dbConn) throws SQLException {
                Statement sql = dbConn.createStatement();
                long startTime=System.currentTimeMillis();
                ResultSet resultSet = sql.executeQuery("select top "+topNum+" ID,XM,XB,MZ,IDCARD,CSRQ,AREA_XIAN,AREA_ZHENG,AREA_CUN,AREA_GRIDUNIT,HJS,HJSHI,HJX,HJDZ,HJLX from TPE_INFLOW_INFO order by ID");
                long endTime=System.currentTimeMillis();
                System.out.println("获取ResultSet时间:"+(endTime-startTime));
                return resultSet;
            }
        }, new IResultSetHandler<List<LinkedHashMap<String,Object>>>() {
            @Override
            public List<LinkedHashMap<String,Object>> ResultSetHandler(ResultSet resultSet) throws SQLException {
                long startTime=System.currentTimeMillis();
                /*List<Map> result=new ArrayList<>();
                while (resultSet.next()){
                    Map map=new HashMap();
                    map.put("id",resultSet.getObject("id"));
                    map.put("name",resultSet.getObject("name"));
                    map.put("sex",resultSet.getObject("sex"));
                    map.put("age",resultSet.getObject("age"));
                    map.put("idcard",resultSet.getObject("idcard"));
                    map.put("brithday",resultSet.getObject("brithday"));
                    result.add(map);
                }*/
                List<LinkedHashMap<String,Object>> results=ResultSetToList(resultSet);
                long endTime=System.currentTimeMillis();
                System.out.println("转换处理时间:"+(endTime-startTime));
                return results;
            }
        });
        return mapList;
    }

    public static List<LinkedHashMap<String,Object>> ResultSetToList(ResultSet rs) throws SQLException{
        List<LinkedHashMap<String,Object>> results=new ArrayList<LinkedHashMap<String,Object>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount=rsmd.getColumnCount();
        List<String> colNameList=new ArrayList<String>();
        for(int i=0;i<colCount;i++){
            colNameList.add(rsmd.getColumnName(i+1));
        }
        while(rs.next()){
            LinkedHashMap map=new LinkedHashMap<String, Object>();
            for(int i=0;i<colCount;i++){
                String key=colNameList.get(i);
                Object value=rs.getString(colNameList.get(i));
                map.put(key, value);
            }
            results.add(map);
        }
        return results;
    }
}
