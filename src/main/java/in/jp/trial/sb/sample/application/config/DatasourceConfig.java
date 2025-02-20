package in.jp.trial.sb.sample.application.config;

import in.jp.trial.sb.sample.application.FileUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
})
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceConfig {

    @Autowired
    Environment env;

    private String url;

    private String driverClassName;

    private String username;

    private String password;

    @PostConstruct
    void checkResources(){
        String defaultFilePath = "/application.properties";
        String profile = env.getProperty("spring.profiles.active");
        String profileFilePath = "/application-"+ profile + ".properties";
        FileUtils.checkFileExistAtLeastOne(defaultFilePath, profileFilePath);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
