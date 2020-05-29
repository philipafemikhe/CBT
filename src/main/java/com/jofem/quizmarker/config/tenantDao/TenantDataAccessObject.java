package com.jofem.quizmarker.config.tenantDao;


import com.jofem.quizmarker.config.SpringDataConfiguration;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.shared.ActiveTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import static org.springframework.core.SpringProperties.getProperty;

@Component
public class TenantDataAccessObject {

    private DataSource ds = null;

//    @Autowired
//    public TenantDb tenantDb;

    @Autowired
    private SpringDataConfiguration springDataConfiguration;

    @Autowired
    @Qualifier("jdbcTenant")
    private JdbcTemplate jdbcTemplateTenant;

    @Autowired


    public JdbcTemplate setDataSource() {
        //TenantDb tenantDb = new TenantDb();
        String dbName = ActiveTenant.tenantDatabase;
        System.out.println("Establishing tenant connection to db: " + dbName);
        DataSource ds = null;
        if(dbName == null){

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
            ds.getConnection().createStatement().execute("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new JdbcTemplate(ds);



//        DataSource ds = springDataConfiguration.tenantDataSource();
//        return new JdbcTemplate(ds);
    }

    public JdbcTemplate setDataSource(User user) {

        ds= DataSourceBuilder
                .create()
                .username("root")
                .password("")
                .url("jdbc:mysql://localhost:3306/examiner_" + user.getId()  + "?serverTimezone=UTC&createDatabaseIfNotExist=true")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();

        try {
            ds.getConnection().createStatement().execute("CREATE DATABASE IF NOT EXISTS examiner_" + user.getId());
            this.initializeTenantDatabase("examiner_" + user.getId());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("Setting datasource...");
//        //springDataConfiguration.setDbName(dbName);
//        springDataConfiguration.dbName = dbName;
//        DataSource ds = springDataConfiguration.tenantDataSource();
//        System.out.println(springDataConfiguration.dbName);
        return new JdbcTemplate(ds);
    }

    public void initializeTenantDatabase(String dbName) throws IOException {
        System.out.println("Initializing database with sql  " + getProperty("user.dir") + "/src/main/resources/static/setup_files/setup.txt");
        try {
//           FileInputStream f1 = new FileInputStream(getProperty("user.dir") + "/src/main/resources/static/setup_files/setup.txt");
//           // File f = new File( "/setup.sql");
//            System.out.println("Processing file of size: " + f1.available());


            File score = new File(getProperty("user.dir") + "/src/main/resources/static/setup_files/score.txt");
            System.out.println("Initializing scores table...");
            String content = new String(Files.readAllBytes(score.toPath()));
            ds.getConnection().createStatement().execute(content);
            System.out.println("Initialization completed for scores");

            File course = new File(getProperty("user.dir") + "/src/main/resources/static/setup_files/courses.sql");
            System.out.println("Initializing courses table...");
            content = new String(Files.readAllBytes(course.toPath()));
            ds.getConnection().createStatement().execute(content);
            System.out.println("Initialization completed for courses");

            File classes = new File(getProperty("user.dir") + "/src/main/resources/static/setup_files/classes.txt");
            System.out.println("Initializing classas table...");
            content = new String(Files.readAllBytes(classes.toPath()));
            ds.getConnection().createStatement().execute(content);
            System.out.println("Initialization completed for classes");


            File question = new File(getProperty("user.dir") + "/src/main/resources/static/setup_files/questions.txt");
            System.out.println("Initializing questions table...");
            content = new String(Files.readAllBytes(question.toPath()));
            ds.getConnection().createStatement().execute(content);
            System.out.println("Initialization completed for questions");


            System.out.println(content);
            ds.getConnection().createStatement().execute(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



//        String str= "CREATE TABLE IF NOT EXISTS categories (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,name varchar(255) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
//        String str2= "CREATE TABLE IF NOT EXISTS questions (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,question varchar(255) COLLATE utf8_unicode_ci NOT NULL,option1 varchar(255) COLLATE utf8_unicode_ci NOT NULL,option2 varchar(255) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
//        String str3= "CREATE TABLE IF NOT EXISTS courses (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,title varchar(150) COLLATE utf8_unicode_ci NOT NULL,description varchar(255) COLLATE utf8_unicode_ci NOT NULL,cu int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
//        try {
//            ds.getConnection().createStatement().execute(str);
//            ds.getConnection().createStatement().execute(str2);
//            ds.getConnection().createStatement().execute(str3);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    public static JdbcTemplate logoutToMaster() {
        DataSource ds= DataSourceBuilder
                .create()
                .username("root")
                .password("")
                .url("jdbc:mysql://localhost:3306/exammarker?serverTimezone=UTC&createDatabaseIfNotExist=true")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();

        return new JdbcTemplate(ds);
    }
}
