package SpringJDBCTemplate003TemplateV001;

import Base.CalculateRunTimeDefault;
import Base.ICalculateRunTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemplateQuery {

    protected void InsertPersonForQuery(int num, JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");
        String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
        List<Object[]> params=new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Object[] singleRecordParams=new Object[6];
            singleRecordParams[0]=i;
            singleRecordParams[1]="InsertPersonForQuery";
            singleRecordParams[2]="男";
            singleRecordParams[3]="98";
            singleRecordParams[4]="445102";
            singleRecordParams[5]="1982-06-12";
            params.add(singleRecordParams);
        }
        jdbcOperations.batchUpdate(sqlString,params);

        System.out.println("成功了插入了" + num + "行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("插入执行时间:"+runTime);
    }

    public List<Person> QueryPersonByRowMapper(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);
        long startTime = System.currentTimeMillis();
        String sql="select * from person";
        List<Person> personList=jdbcOperations.query(sql,new PersonRowMapper());
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);

        return personList;
    }

    public List<Person> QueryPersonByPreparedStatementRowMapper(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        List<Person> personList=jdbcOperations.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement=connection.prepareStatement("select * from person WHERE id<?");
                preparedStatement.setObject(1,100000);
                return preparedStatement;
            }
        },new PersonRowMapper());
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);

        return personList;
    }

    public List<Person> QueryPersonByPreparedStatementSetterRowMapper(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num, jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql = "select * from person where id<?";
        List<Person> personList = jdbcOperations.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1, 100000);
            }
        }, new PersonRowMapper());
        long endTime = System.currentTimeMillis();
        long runTime = (endTime - startTime);
        System.out.println("执行查询转换时间:" + runTime);

        return personList;
    }

    public List<Person> QueryPersonByParasRowMapper(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql = "select * from person where id<?";
        Object[] paras=new Object[1];
        paras[0]=10000;
        List<Person> personList=jdbcOperations.query(sql,paras,new PersonRowMapper());
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personList;
    }

    public List<Person> QueryPersonByResultSetExtractor(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select * from person";
        List<Person> personList=jdbcOperations.query(sql, new ResultSetExtractor<List<Person>>() {
            @Override
            public List<Person> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Person> result=new ArrayList<Person>();
                PersonRowMapper personRowMapper=new PersonRowMapper();
                while (resultSet.next()){
                    if(resultSet.getInt("id")>50){
                        result.add(personRowMapper.mapRow(resultSet,0));
                    }
                }
                return result;
            }
        });
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personList;
    }

    public List<Person> QueryPersonByRowCallbackHandler(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql = "select * from person where id<?";
        Object[] paras=new Object[1];
        paras[0]=10000;
        List<Person> personList=new ArrayList<>();
        PersonRowMapper personRowMapper=new PersonRowMapper();
        jdbcOperations.query(sql,paras, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                personList.add(personRowMapper.mapRow(resultSet, 0));
            }
        });
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personList;
    }
}
