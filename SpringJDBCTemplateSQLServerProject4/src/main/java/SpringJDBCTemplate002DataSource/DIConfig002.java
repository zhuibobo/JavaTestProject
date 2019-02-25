package SpringJDBCTemplate002DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/21
 * @Description:
 * @Version 1.0.0
 */
@Configuration
public class DIConfig002 {
    @Bean
    public DataSource dataSource(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        /*try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverName);
        driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433; DatabaseName=SpringJDBCTemplateProject4");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("sql");
        return driverManagerDataSource;
    }
}
