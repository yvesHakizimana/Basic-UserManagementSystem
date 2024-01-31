package com.code.empcrud.empcrud.web;

import com.code.empcrud.empcrud.dao.UserDaoImpl;
import com.code.empcrud.empcrud.model.User;
import com.code.empcrud.empcrud.utils.Dbconnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "UpdateUser", value = "/update-user")
public class UpdateUser extends HttpServlet {

    UserDaoImpl userDao;
    Dbconnection dbconnection;

    public void init(){
        this.userDao = new UserDaoImpl();
        this.dbconnection = new Dbconnection();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        Cookie[] cookies = req.getCookies();
        int id = 0;

        for(Cookie c : cookies){
            if(c.getName().equals("id"))
                id = Integer.parseInt(c.getValue());
        }

        String name =  req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        int result = userDao.updateUserById(user, dbconnection);
        if(result == 1)
        {
            List<User> users = new ArrayList<>();
            users = userDao.selectUsers(dbconnection);
            req.setAttribute("users", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(req, res);
        }
        else
        {
            PrintWriter out = res.getWriter();
            out.println("There is a glitch within the request microsecond to be resolved");
        }
    }


}

