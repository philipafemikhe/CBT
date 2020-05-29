package com.jofem.quizmarker.dbUtil;

//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfig {

    @Autowired
    private  Environment env;

    public Connection getConnection()throws SQLException {

        System.out.println("Connecting to database: " + env.getProperty("spring.datasource.url"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
        try{
            Connection myConn =
                    DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
            System.out.println("Connection successful!!");
            return myConn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
