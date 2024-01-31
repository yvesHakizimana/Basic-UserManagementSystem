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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "DeleteUser", value = "/delete-user")
public class deleteUser extends HttpServlet {

    UserDaoImpl userDao;
    Dbconnection dbconnection;

    public void init(){
        this.dbconnection = new Dbconnection();
        this.userDao  = new UserDaoImpl();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        boolean result = userDao.deleteUserById(id, dbconnection);
        if(result){
            List<User> users = new ArrayList<>();
            users = userDao.selectUsers(dbconnection);
            req.setAttribute("users", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(req, res);
        }
        else {
            PrintWriter out = res.getWriter();
            out.println("There is a glitch within the request microsecond to be resolved");
        }
    }
}
