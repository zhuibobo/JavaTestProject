/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package CamundaProject13;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("mySimpleApplication")
public class MainApplication implements CommandLineRunner {

    boolean processApplicationStopped;

    public static void main(final String... args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
        //System.out.println(processEngine);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSource dataSourceBean() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        comboPooledDataSource.setJdbcUrl("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=CamundaProject13P001");
        comboPooledDataSource.setUser("sa");
        comboPooledDataSource.setPassword("sql");
        //ComboPooledDataSource validationquery
        comboPooledDataSource.setPreferredTestQuery("SELECT 1");
        comboPooledDataSource.setAutomaticTestTable("TestConn");
        comboPooledDataSource.setIdleConnectionTestPeriod(60);
        //comboPooledDataSource.sett
        return comboPooledDataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSourceBean());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("***************************");
        System.out.println(CamundaUtility.getProcessEngine());
        System.out.println(ProcessEngines.getDefaultProcessEngine());
        //System.out.println(processEngine.getRepositoryService());
    }
}
