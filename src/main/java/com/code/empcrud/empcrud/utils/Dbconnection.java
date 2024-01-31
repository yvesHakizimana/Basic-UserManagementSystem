package com.code.empcrud.empcrud.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static String jdbcUrl;
    private static String jdbcUsername;
    private static String jdbcPassword;

    public Connection connection() throws SQLException {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch(SQLException ex){
            throw new RuntimeException("SQLException");
        } catch(ClassNotFoundException ex){
            throw  new RuntimeException("ClassNotFoundException");
        }
        return conn;
    }
}
