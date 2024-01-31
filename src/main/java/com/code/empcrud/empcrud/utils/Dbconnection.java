package com.code.empcrud.empcrud.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/webapp";
    private static String jdbcUsername = "postgres";
    private static String jdbcPassword = "P0S1tiv@!";

    public Connection connectToDb() throws SQLException {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            if(conn != null)
                System.out.println("We are connected to the database successfully");
        } catch(SQLException ex){
            throw new RuntimeException("SQLException");
        } catch(ClassNotFoundException ex){
            throw  new RuntimeException("ClassNotFoundException");
        }
        return conn;
    }
}
