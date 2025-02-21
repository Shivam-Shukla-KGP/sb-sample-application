package in.jp.trial.sb.sample.application.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.config.TaskExecutorFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
@EnableBatchProcessing
public class ApplicationBatchConfiguration {

    @Autowired
    @Qualifier("dataSource")
    DataSource batchDataSource;

    @Autowired
    @Qualifier("transactionManager")
    TransactionManager batchTransactionManager;

    @Bean
    public JobRepositoryFactoryBean jobRepositoryFactoryBean() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(batchDataSource);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.H2.toString());
        jobRepositoryFactoryBean.setTransactionManager((PlatformTransactionManager) batchTransactionManager);
        jobRepositoryFactoryBean.setCharset(StandardCharsets.UTF_8);
        jobRepositoryFactoryBean.afterPropertiesSet();
        System.out.println("Factory Bean used");
        return jobRepositoryFactoryBean;
    }

    @Bean
    public JobRepository jobRepository(JobRepositoryFactoryBean jobRepositoryFactoryBean) throws Exception {
        System.out.println("job Repository Bean used");
        return (JobRepository) jobRepositoryFactoryBean.getObject();
//        return (JobRepository) createJobExplorerFactoryBean().getObject();
    }


    @Bean
    public JobExplorerFactoryBean jobExplorerFactoryBean() throws Exception {
        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(batchDataSource);
        jobExplorerFactoryBean.setCharset(StandardCharsets.UTF_8);
        jobExplorerFactoryBean.setTransactionManager((PlatformTransactionManager) batchTransactionManager);
        jobExplorerFactoryBean.afterPropertiesSet();
        System.out.println("Job Explorer Factory Bean Used");
        return jobExplorerFactoryBean;
    }

    @Bean
    public JobExplorer jobExplorer(JobExplorerFactoryBean jobExplorerFactoryBean) throws Exception {
        System.out.println("Job Explorer Bean used");
        return jobExplorerFactoryBean.getObject();
    }

    @Bean
    public JobLauncher jobLauncher(TaskExecutor taskExecutor, @Qualifier("jobRepository") JobRepository jobRepository) {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(taskExecutor);
        return jobLauncher;
    }

//    @Bean
//    public JobLauncher jobLauncher(TaskExecutor taskExecutor, @Qualifier("jobRepository") JobRepository jobRepository) {
//        JobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(taskExecutor);
//        return jobLauncher;
//    }

    @Bean
    public TaskExecutor taskExecutor() {
        TaskExecutorFactoryBean taskExecutorFactoryBean = new TaskExecutorFactoryBean();
        taskExecutorFactoryBean.setBeanName("sampleApplicationTaskExecutor");
        taskExecutorFactoryBean.afterPropertiesSet();
        return taskExecutorFactoryBean.getObject();
    }

}
