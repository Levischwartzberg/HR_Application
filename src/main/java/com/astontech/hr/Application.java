package com.astontech.hr;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SpringBootApplication
public class Application {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.driverClassName}")
    private String dataSourceDriverClassName;

    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(dataSourceDriverClassName);
        ds.setUrl(dataSourceUrl);
        ds.setUsername(dataSourceUsername);
        ds.setPassword(dataSourcePassword);
        return ds;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
