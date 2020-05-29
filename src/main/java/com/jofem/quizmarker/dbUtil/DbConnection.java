package com.jofem.quizmarker.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/exammarker?serverTimezone=UTC";


    public static Connection getConnection()throws SQLException{



        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, USERNAME, PASSWORD);
            System.out.println("Connection successful!!");
            return myConn;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
