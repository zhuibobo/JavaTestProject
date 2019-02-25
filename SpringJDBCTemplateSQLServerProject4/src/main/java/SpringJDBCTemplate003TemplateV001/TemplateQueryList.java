package SpringJDBCTemplate003TemplateV001;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Map;

public class TemplateQueryList extends TemplateQuery {
    public List<Map<String, Object>> QueryForListSQL(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select * from person";
        List<Map<String, Object>> personList=jdbcOperations.queryForList(sql);

        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personList;
    }

    public List<Integer> QueryForListSQLClassT(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select id from person";
        List<Integer> personIdList=jdbcOperations.queryForList(sql,int.class);

        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personIdList;
    }

    public List<Integer> QueryForListParasSQLClassT(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select id from person WHERE id>?";
        Object[] paras=new Object[1];
        paras[0]=0;
        List<Integer> personIdList=jdbcOperations.queryForList(sql,paras,int.class);
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return personIdList;
    }
}
