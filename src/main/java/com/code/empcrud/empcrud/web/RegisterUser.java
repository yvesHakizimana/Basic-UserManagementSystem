package com.code.empcrud.empcrud.web;

import com.code.empcrud.empcrud.dao.UserDaoImpl;
import com.code.empcrud.empcrud.model.User;
import com.code.empcrud.empcrud.utils.Dbconnection;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( name = "RegisterUser", value = "/register-user")
public class RegisterUser extends HttpServlet {

    Dbconnection dbconnection;
    UserDaoImpl userDao;

    public void init(){
        this.dbconnection = new Dbconnection();
        this.userDao = new UserDaoImpl();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User user =  new User(name , email, country);
        boolean isInserted = userDao.insertUser(user, dbconnection);
        PrintWriter out =  res.getWriter();
        if(isInserted)
            res.sendRedirect("list-users");
        else{
            out.println("Sorry there is an glitch a microsecond to be resolved");
        }
    }

}
