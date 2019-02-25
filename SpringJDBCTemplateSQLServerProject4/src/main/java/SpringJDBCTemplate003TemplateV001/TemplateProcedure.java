package SpringJDBCTemplate003TemplateV001;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateProcedure {

    private int GetPersonCount( JdbcOperations jdbcOperations){
        int count=jdbcOperations.query("select count(*) from person", new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt(1);
            }
        });
        return count;
    }

    public Person CallSPInsertPersonNotOut(JdbcOperations jdbcOperations){
        jdbcOperations.execute("DELETE person");
        jdbcOperations.execute("exec sp_insert_person_not_out 1,'name','男',18,'445102','1982-06-12'");

        String sql="select * from person where id=?";
        Object[] paras=new Object[1];
        paras[0]=1;
        Person person =jdbcOperations.queryForObject(sql,paras,new PersonRowMapper());
        return person;
    }

    public int CallSPInsertPersonNotOutByNum(int num,JdbcOperations jdbcOperations){
        jdbcOperations.execute("DELETE person");

        long startTime = System.currentTimeMillis();
        for (int i=0;i<num;i++) {
            jdbcOperations.execute("exec sp_insert_person_not_out "+i+",'name','男',18,'445102','1982-06-12'");
        }
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行存储过程时间:"+runTime);

        return GetPersonCount(jdbcOperations);
    }

    public Map<String,String> CallSPInsertPersonParamsOut(JdbcOperations jdbcOperations){
        jdbcOperations.execute("DELETE person");
        return jdbcOperations.execute(
                new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                        String storedProc = "exec sp_insert_person_out_param ?,?,?,?,?,?,?,?";// 调用的sql
                        CallableStatement cs = connection.prepareCall(storedProc);
                        cs.setInt(1, 1);// 设置输入参数的值
                        cs.setString(2,"CallSPInsertPersonParamsOut");
                        cs.setString(3,"男");
                        cs.setInt(4,18);
                        cs.setString(5,"445102");
                        cs.setString(6,"1982-06-12");
                        cs.registerOutParameter(7, Types.VARCHAR);// 注册输出参数的类型
                        cs.registerOutParameter(8,Types.VARCHAR);
                        return cs;
                    }
                }, new CallableStatementCallback<Map<String,String>>() {
                    public Map<String,String> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                        cs.execute();
                        Map<String,String> result=new HashMap<>();
                        result.put("outidcard",cs.getString(7));
                        result.put("outname",cs.getString(8));
                        return result;// 获取输出参数的值
                    }
                });
    }

    public List<Person> CallSPInsertPersonParamsCursor(int num,JdbcOperations jdbcOperations){
        //SQL Server 不支持游标输出?
        TemplateInsert templateInsert=new TemplateInsert();
        templateInsert.InsertPersonParasBatch(num,jdbcOperations);
        List<Person> personList=jdbcOperations.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "exec sp_select_person_cursor ?";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        //cs.setString(1, "p1");// 设置输入参数的值
                        cs.registerOutParameter(1,Types.REF_CURSOR);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback<List<Person>>() {
                    public List<Person> doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        List<Person> personList1=new ArrayList<>();
                        cs.execute();
                        //ResultSet rs =cs.getResultSet();
                        ResultSet rs = (ResultSet) cs.getObject(1);// 获取游标一行的值
                        PersonRowMapper personRowMapper=new PersonRowMapper();
                        while (rs.next()) {// 转换每行的返回值到Map中
                            personList1.add(personRowMapper.mapRow(rs,0));
                        }
                        rs.close();
                        return personList1;
                    }
                });
        return personList;
    }

    public List<Person> CallSPInsertPersonSelect(int num,JdbcOperations jdbcOperations){
        TemplateInsert templateInsert=new TemplateInsert();
        templateInsert.InsertPersonParasBatch(num,jdbcOperations);
        List<Person> personList=jdbcOperations.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "exec sp_select_person";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        return cs;
                    }
                }, new CallableStatementCallback<List<Person>>() {
                    public List<Person> doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        List<Person> personList1=new ArrayList<>();
                        cs.execute();
                        ResultSet rs =cs.getResultSet();
                        PersonRowMapper personRowMapper=new PersonRowMapper();
                        while (rs.next()) {// 转换每行的返回值到Map中
                            personList1.add(personRowMapper.mapRow(rs,0));
                        }
                        rs.close();
                        return personList1;
                    }
                });
        return personList;
    }

    public List<PersonNewC> CallSPInsertPersonSelect2Result(int num,JdbcOperations jdbcOperations){
        TemplateInsert templateInsert=new TemplateInsert();
        templateInsert.InsertPersonParasBatch(num,jdbcOperations);
        List<PersonNewC> personList=jdbcOperations.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "exec sp_select_person_2_result";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        return cs;
                    }
                }, new CallableStatementCallback<List<PersonNewC>>() {
                    public List<PersonNewC> doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        List<PersonNewC> personList1=new ArrayList<>();
                        cs.execute();
                        ResultSet rs =cs.getResultSet();
                        rs.close();
                        boolean more=cs.getMoreResults();
                        rs=cs.getResultSet();
                        while (rs.next()) {
                            PersonRowMapperNewC personRowMapperNewC = new PersonRowMapperNewC();
                            personList1.add(personRowMapperNewC.mapRow(rs, 0));
                        }
                        return personList1;
                    }
                });
        return personList;
    }
}
