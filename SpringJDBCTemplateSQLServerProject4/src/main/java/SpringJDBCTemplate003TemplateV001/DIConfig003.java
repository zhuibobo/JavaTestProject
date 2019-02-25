package SpringJDBCTemplate003TemplateV001;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/21
 * @Description:
 * @Version 1.0.0
 */
@Configuration
public class DIConfig003 {
    @Bean
    //@Scope("prototype")
    public DataSource dataSource(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverName);
        driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("sql");
        return driverManagerDataSource;
    }

    @Bean
    //@Scope("prototype")
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
