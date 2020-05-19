package SpringJDBCTemplate003TemplateV001;

import Base.CalculateRunTimeDefault;
import Base.ICalculateRunTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemplateInsert {

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

    public int InsertPersonByExecuteString(int num, JdbcOperations jdbcOperations) {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                jdbcOperations.execute("DELETE person");
                for (int i = 0; i < num; i++) {
                    String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(" + i + ",'InsertPersonByExecuteString','男','99','445102','1982-06-12')";
                    jdbcOperations.execute(insertSql);
                }
                System.out.println("成功了插入了" + num + "行");
            }
        },"");
        return GetPersonCount(jdbcOperations);
    }

    public int InsertPersonByExecuteStatementCallback(int num, JdbcOperations jdbcOperations) {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                jdbcOperations.execute("DELETE person");
                for (int i = 0; i < num; i++) {
                    String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(" + i + ",'InsertPersonByExecuteStatementCallback','男','99','445102','1982-06-12')";
                    jdbcOperations.execute(new StatementCallback<Person>() {
                        @Override
                        public Person doInStatement(Statement stmt) throws SQLException, DataAccessException {
                            //添加在同一个事务中的操作。
                            stmt.execute(insertSql);
                            return null;
                        }
                    });
                }
                System.out.println("成功了插入了" + num + "行");
            }
        },"");
        return GetPersonCount(jdbcOperations);
    }

    public int InsertPersonByExecuteConnectionCallback(int num, JdbcOperations jdbcOperations) {
        CalculateRunTimeDefault.CalculateRunTimeDefault(new ICalculateRunTime() {
            @Override
            public void Run() {
                jdbcOperations.execute("DELETE person");
                for (int i = 0; i < num; i++) {
                    String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(" + i + ",'zrb','男','99','445102','1982-06-12')";
                    jdbcOperations.execute(new ConnectionCallback<Person>() {
                        @Override
                        public Person doInConnection(Connection con) throws SQLException, DataAccessException {
                            con.createStatement().execute(insertSql);
                            return null;
                        }
                    });
                }
                System.out.println("成功了插入了" + num + "行");
            }
        },"");
        return GetPersonCount(jdbcOperations);
    }

    public int InsertPersonByExecutePreparedStatementCallback(int num, JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");
        for (int i = 0; i < num; i++) {
            String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
            int finalI = i;
            jdbcOperations.execute(insertSql, new PreparedStatementCallback<Person>() {
                @Override
                public Person doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    //ps.setClob();
                    ps.setObject(1, finalI);
                    ps.setObject(2,"InsertPersonByExecutePreparedStatementCallback");
                    ps.setObject(3,"男");
                    ps.setObject(4,"99");
                    ps.setObject(5,"445102");
                    ps.setObject(6,"1982-06-12");
                    ps.execute();
                    return null;
                }
            });
        }
        System.out.println("成功了插入了" + num + "行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public int InsertPersonParams(JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");
        String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
        jdbcOperations.update(insertSql, new Object[]{1,"InsertPersonParams","男",99,"445102","1982-06-11"});

        System.out.println("成功了插入了1行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public long InsertPersonPreparedStatement(JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");
        String insertSql = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
        jdbcOperations.update(insertSql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1,1);
                preparedStatement.setObject(2,"InsertPersonPreparedStatement");
                preparedStatement.setObject(3,"男");
                preparedStatement.setObject(4,"99");
                preparedStatement.setObject(5,"445102");
                preparedStatement.setObject(6,"1982-06-12");
            }
        });

        System.out.println("成功了插入了1行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    /**
     * 表自动生成主键,插入数据并获取键值
     * @param jdbcOperations
     * @return
     */
    public long InsertPersonGeneratedKeyHolder(JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person_auto_id");
        String insertSql = "INSERT INTO person_auto_id(name,sex,age,idcard,brithday) VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement=connection.prepareStatement(insertSql, new String[]{"pid"});
                preparedStatement.setObject(1,"InsertPersonGeneratedKeyHolder");
                preparedStatement.setObject(2,"男");
                preparedStatement.setObject(3,"99");
                preparedStatement.setObject(4,"445102");
                preparedStatement.setObject(5,"1982-06-12");
                return preparedStatement;
            }
        }, keyHolder);
        System.out.println("自动生成主键"+keyHolder.getKey());
        System.out.println("成功了插入了1行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public long InsertPersonStringBatch(int num, JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");

        String sqlString;
        String[] sqlList=new String[num];
        for (int i = 0; i < num; i++) {
            sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(" + i + ",'zrb','男','99','445102','1982-06-12')";
            sqlList[i]=sqlString;
        }
        jdbcOperations.batchUpdate(sqlList);

        System.out.println("成功了插入了记录:"+num);
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public long InsertPersonParasBatch(int num, JdbcOperations jdbcOperations) {
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");

        String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";
        List<Object[]> params=new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Object[] singleRecordParams=new Object[6];
            singleRecordParams[0]=i;
            singleRecordParams[1]="zrb";
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
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public long InsertPersonPreparedStatementBatch(int num,JdbcOperations jdbcOperations){
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");

        String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";

        jdbcOperations.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setObject(1,i);
                preparedStatement.setObject(2,"zrb");
                preparedStatement.setObject(3,"男");
                preparedStatement.setObject(4,"98");
                preparedStatement.setObject(5,"445102");
                preparedStatement.setObject(6,"1982-06-12");
            }

            @Override
            public int getBatchSize() {
                return num;
            }
        });

        System.out.println("成功了插入了" + num + "行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }

    public long InsertPersonPreparedStatementTBatch(List<Person> persons,JdbcOperations jdbcOperations){
        long startTime = System.currentTimeMillis();
        jdbcOperations.execute("DELETE person");

        String sqlString = "INSERT INTO person(id,name,sex,age,idcard,brithday) VALUES(?,?,?,?,?,?)";

        jdbcOperations.batchUpdate(sqlString, persons, persons.size(), new ParameterizedPreparedStatementSetter<Person>() {
            @Override
            public void setValues(PreparedStatement preparedStatement, Person person) throws SQLException {
                preparedStatement.setObject(1,person.getId());
                preparedStatement.setObject(2,person.getName());
                preparedStatement.setObject(3,person.getSex());
                preparedStatement.setObject(4,person.getAge());
                preparedStatement.setObject(5,person.getIdcard());
                preparedStatement.setObject(6,person.getBrithday());

            }
        });

        System.out.println("成功了插入了" + persons.size() + "行");
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行时间:"+runTime);
        return GetPersonCount(jdbcOperations);
    }
}
