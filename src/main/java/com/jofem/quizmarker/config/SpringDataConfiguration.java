package com.jofem.quizmarker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
@ComponentScan(basePackages = {"com.jofem.quizmarker"})
@EnableWebMvc
//@ImportResource("classpath:/pom.xml")
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringDataConfiguration {
    @Autowired
    Environment env;

    public String dbName = "exammarker_default_schema";

    @Bean
    @Primary
    public DataSource mainDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

 @Bean(name = "tenantDataSource")
    public DataSource tenantDataSource(){
        System.out.println("Establishing tenant connection to db: " + this.dbName);
        DataSource ds = null;
        if(this.dbName == null){

            ds= DataSourceBuilder
                    .create()
                    .username("root")
                    .password("")
                    .url("jdbc:mysql://localhost:3306/exammarker_default_schema?serverTimezone=UTC&createDatabaseIfNotExist=true")
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();
        } else{
            System.out.println("DbName is " + dbName);
            ds= DataSourceBuilder
                    .create()
                    .username("root")
                    .password("")
                    .url("jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC&createDatabaseIfNotExist=true")
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();
        }
        try {
            ds.getConnection().createStatement().execute("CREATE DATABASE IF NOT EXISTS " + this.dbName);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ds;

    }

    @Bean
    public JdbcTemplate jdbcTemplateMain(@Qualifier("mainDataSource") DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Bean(name = "jdbcTenant")
    public JdbcTemplate jdbcTemplateTenant(@Qualifier("tenantDataSource") DataSource ds) {
        System.out.print("Establishing tenant connection to db with database name: " + this.dbName);
        return new JdbcTemplate(ds);
    }
}
