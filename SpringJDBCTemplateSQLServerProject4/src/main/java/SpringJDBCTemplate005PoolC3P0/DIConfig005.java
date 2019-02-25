package SpringJDBCTemplate005PoolC3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DIConfig005 {
    @Bean(destroyMethod="close")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverName);
        comboPooledDataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4");
        comboPooledDataSource.setUser("sa");
        comboPooledDataSource.setPassword("sql");
        return comboPooledDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
