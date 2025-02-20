package in.jp.trial.sb.sample.application.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.nio.charset.Charset;

@Component
@EnableBatchProcessing
public class ApplicationBatchConfiguration {

    @Autowired
    @Qualifier("dataSource")
    DataSource batchDataSource;

    @Autowired
    @Qualifier("transactionManager")
    TransactionManager batchTransactionManager;

    @Bean(name = "jobRepository")
    public JobRepositoryFactoryBean createJobRepositoryFactoryBean() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(batchDataSource);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.H2.toString());
        jobRepositoryFactoryBean.setTransactionManager((PlatformTransactionManager) batchTransactionManager);
        jobRepositoryFactoryBean.setCharset(Charset.forName("UTF-8"));
        jobRepositoryFactoryBean.afterPropertiesSet();
        System.out.println("Factory Bean used");
        return jobRepositoryFactoryBean;
    }

    @Bean
    public JobRepository createJobRepository(JobRepositoryFactoryBean jobRepositoryFactoryBean) throws Exception {
        System.out.println("job Repository Bean used");
        return jobRepositoryFactoryBean.getObject();
    }




}
