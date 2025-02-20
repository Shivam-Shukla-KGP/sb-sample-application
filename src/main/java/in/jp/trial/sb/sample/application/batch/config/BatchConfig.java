package in.jp.trial.sb.sample.application.batch.config;

import in.jp.trial.sb.sample.application.FileUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources(
        {@PropertySource(value = "classpath:batch/batch.properties", ignoreResourceNotFound = true, encoding = "UTF-8"),
         @PropertySource(value = "classpath:batch/batch-${spring.profiles.active}.properties", ignoreResourceNotFound = true, encoding = "UTF-8")}
)
@ConfigurationProperties(prefix = "sample.application.batch")
public class BatchConfig {

    @Autowired
    Environment env;

    @PostConstruct
    void checkResources(){
        String defaultFilePath = "/batch/batch.properties";
        String profile = env.getProperty("spring.profiles.active");
        String profileFilePath = "/batch/batch-"+ profile + ".properties";
        FileUtils.checkFileExistAtLeastOne(defaultFilePath, profileFilePath);
    }
}
