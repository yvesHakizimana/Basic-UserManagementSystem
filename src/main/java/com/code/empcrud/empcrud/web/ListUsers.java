package com.code.empcrud.empcrud.web;

import com.code.empcrud.empcrud.dao.UserDaoImpl;
import com.code.empcrud.empcrud.model.User;
import com.code.empcrud.empcrud.utils.Dbconnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "ListUsers", value = "/list-users")
public class ListUsers extends HttpServlet {

    Dbconnection dbconnection;
    UserDaoImpl userDao;

    public void init(){
        this.dbconnection = new Dbconnection();
        this.userDao = new UserDaoImpl();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        List<User> users = new ArrayList<>();
        users = userDao.selectUsers(dbconnection);
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, res);
    }
}
