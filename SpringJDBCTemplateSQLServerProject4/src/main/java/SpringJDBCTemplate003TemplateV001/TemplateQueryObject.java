package SpringJDBCTemplate003TemplateV001;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/27
 * @Description:
 * @Version 1.0.0
 */
public class TemplateQueryObject extends TemplateQuery {
    public Integer QueryForObject(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select id from person where id=?";
        Object[] paras=new Object[1];
        paras[0]=0;
        Integer personid =jdbcOperations.queryForObject(sql,paras,Integer.class);

        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personid;
    }

    public Person QueryForObjectRowMapper(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select * from person where id=?";
        Object[] paras=new Object[1];
        paras[0]=0;
        Person person =jdbcOperations.queryForObject(sql,paras,new PersonRowMapper());

        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return person;
    }
}
