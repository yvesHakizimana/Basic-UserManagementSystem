package com.code.empcrud.empcrud.dao;

import com.code.empcrud.empcrud.model.User;
import com.code.empcrud.empcrud.utils.Dbconnection;

import java.util.List;

public interface userDao {
    boolean insertUser (User user, Dbconnection dbconnection);
    List<User> selectUsers(Dbconnection dbconnection);
    User selectUserById(long id, Dbconnection dbconnection);
    int updateUserById(User user , Dbconnection dbconnection);
    boolean deleteUserById(long id, Dbconnection dbconnection);
}
