package SpringJDBCTemplate004PoolDBCP;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DIConfig004 {

    @Bean(destroyMethod="close")
    public BasicDataSource dataSource(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        BasicDataSource basicDataSource=new BasicDataSource();
        basicDataSource.setDriverClassName(driverName);
        basicDataSource.setUrl("jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("sql");
        //basicDataSource.setInitialSize(110);
        basicDataSource.setMaxActive(15);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
