package SpringJDBCTemplate005PoolProxool;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DIConfig006 {
    @Bean
    public ProxoolDataSource dataSource(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        ProxoolDataSource proxoolDataSource=new ProxoolDataSource();
        proxoolDataSource.setDriver(driverName);
        proxoolDataSource.setDriverUrl("jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4");
        proxoolDataSource.setUser("sa");
        proxoolDataSource.setPassword("sql");
        return proxoolDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
