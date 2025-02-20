package in.jp.trial.sb.sample.application.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;


import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    DatasourceConfig datasourceConfig;

    @Bean(name="dataSource")
    DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(datasourceConfig.getUsername());
        config.setPassword(datasourceConfig.getPassword());
        config.setDriverClassName(datasourceConfig.getDriverClassName());
        config.setJdbcUrl(datasourceConfig.getUrl());
        config.setAutoCommit(true);
        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean(name="transactionManager")
    TransactionManager createTransactionManager(DataSource dataSource){
        TransactionManager transactionManager = new JpaTransactionManager();

        //JDBC TransactionManager and DataSourceTransactionManager are not feasible to use with JPA Repositories.
        //TransactionManager transactionManager = new JdbcTransactionManager(dataSource);
        //transactionManager.setDataSource(dataSource);
        //transactionManager.afterPropertiesSet();
        return transactionManager;
    }

}
