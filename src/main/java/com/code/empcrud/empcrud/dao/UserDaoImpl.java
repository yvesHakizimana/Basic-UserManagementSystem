package com.code.empcrud.empcrud.dao;

import com.code.empcrud.empcrud.model.User;
import com.code.empcrud.empcrud.utils.Dbconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements userDao{

    //SQL  Statements
    private static String INSERT_USERS_SQL = "INSERT INTO userdemo " + " (name , email , country) " + " VALUES " + " (?, ?, ?);";
    private static String SELECT_USER_BY_ID = "SELECT id, name ,email , country FROM userdemo  where id = ? ;";
    private static String SELECT_ALL_USERS = "SELECT id, name ,email , country FROM userdemo;";
    private static String  DELETE_USER_BY_ID = "DELETE FROM userdemo where id = ?;";
    private static String UPDATE_USER_BY_ID = "UPDATE userdemo set name = ? ,email = ? , country= ? where id = ? ;";

    Dbconnection dbconnection = new Dbconnection();
    @Override
    public boolean insertUser(User user, Dbconnection dbconnection) {
        boolean isInserted = false;
        try(PreparedStatement statement = dbconnection.connectToDb().prepareStatement(INSERT_USERS_SQL);){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            isInserted = statement.executeUpdate() > 0;
        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return isInserted;
    }

    @Override
    public List<User> selectUsers(Dbconnection dbconnection) {
        List<User> users = new ArrayList<>();
        try(PreparedStatement statement = dbconnection.connectToDb().prepareStatement(SELECT_ALL_USERS);){
            ResultSet resultFromDatabase = statement.executeQuery();
            while(resultFromDatabase.next()){
                long id = resultFromDatabase.getLong("id");
                String name = resultFromDatabase.getString("name");
                String email = resultFromDatabase.getString("email");
                String country = resultFromDatabase.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }

        return users;
    }

    @Override
    public User selectUserById(long id, Dbconnection dbconnection) {
        User user = null;
        try(PreparedStatement statement = dbconnection.connectToDb().prepareStatement(SELECT_USER_BY_ID);){
            statement.setLong(1, id);
            ResultSet rsFromDB = statement.executeQuery();
            while(rsFromDB.next()){
                String name = rsFromDB.getString("name");
                String email = rsFromDB.getString("email");
                String country = rsFromDB.getString("country");
                user = new User(name, email, country);
            }

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public int updateUserById(User user, Dbconnection dbconnection) {
        int result = 0;
        try(PreparedStatement statement = dbconnection.connectToDb().prepareStatement(UPDATE_USER_BY_ID);){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setLong(4, user.getId());
            result = statement.executeUpdate();

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteUserById(long id, Dbconnection dbconnection) {
        boolean isDeleted =  false;
        try(PreparedStatement statement = dbconnection.connectToDb().prepareStatement(DELETE_USER_BY_ID);){
            statement.setLong(1, id);
            isDeleted = statement.executeUpdate() > 0;
        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return isDeleted;
    }
}
