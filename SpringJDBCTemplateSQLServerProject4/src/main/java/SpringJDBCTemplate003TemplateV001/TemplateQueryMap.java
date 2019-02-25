package SpringJDBCTemplate003TemplateV001;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/27
 * @Description:
 * @Version 1.0.0
 */
public class TemplateQueryMap extends TemplateQuery {
    public Map<String, Object> QueryForMap(int num, JdbcOperations jdbcOperations) {
        InsertPersonForQuery(num,jdbcOperations);

        long startTime = System.currentTimeMillis();
        String sql="select * from person where id=?";
        Map<String, Object> map=jdbcOperations.queryForMap(sql,0);
        long endTime = System.currentTimeMillis();
        long runTime=(endTime - startTime);
        System.out.println("执行查询转换时间:"+runTime);
        return map;
    }
}
