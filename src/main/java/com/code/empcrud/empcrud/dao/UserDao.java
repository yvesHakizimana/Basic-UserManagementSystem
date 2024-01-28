package com.code.empcrud.empcrud.dao;

import com.code.empcrud.empcrud.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcUrl = "jdbc:postgresql://locahost:5432/webapp";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "P0S1tiv@!";

    // SQl Statements
    private static String INSERT_USERS_SQL = "INSERT INTO userDemo " + " (name , email , country) " + " VALUES " + " (?, ?, ?);";
    private static String SELECT_USER_BY_ID = "SELECT id, name ,email , country FROM userDemo  where id = ? ;";
    private static String SELECT_ALL_USERS = "SELECT * FROM userDemo;";

    private static String  DELETE_USER_BY_ID = "DELETE FROM userDemo where id = ?;";

    private static String UPDATE_USER_BY_ID = "UPDATE userDemo set name = ? ,email = ? , country= ? where id = ? ;";


    public UserDao(){}

    protected Connection getConnection(){
        Connection connection =  null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) {
        System.out.println(INSERT_USERS_SQL);
        // Try with resource statement will auto close the connection
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch(SQLException ex){
            printSQLException(ex);
        }
    }


    public User selectUser(long id){
        User user = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID); ){
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);


            // Execute the query or update the query
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name , email, country);
            }
        } catch(SQLException ex){
            printSQLException(ex);
        }

        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users =new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }


        } catch(SQLException ex) {
            printSQLException(ex);
        }

        return users;
    }

    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);){

            System.out.println(UPDATE_USER_BY_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setLong(4, user.getId());

            rowUpdated =  preparedStatement.executeUpdate() > 0;

        } catch (SQLException ex){
            printSQLException(ex);
        }

        return rowUpdated;
    }


    public boolean deleteUser(long id) {

        boolean rowDeleted = false;
        try(Connection  connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);){
            System.out.println(DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

            rowDeleted = preparedStatement.executeUpdate()  > 0;


        } catch(SQLException ex){
            printSQLException(ex);
        }

        return rowDeleted;
    }


    private void printSQLException(SQLException ex){
        for(Throwable e : ex){
            if ( e instanceof SQLException ){
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message : " + ((SQLException )e).getMessage());
                Throwable cause = ex.getCause();
                while( cause != null)
                {
                    System.out.println("Cause : " + cause);
                    cause =  cause.getCause();
                }
            }
        }
    }
}
