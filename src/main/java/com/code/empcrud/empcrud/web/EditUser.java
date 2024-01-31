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

@WebServlet(name = "EditUser", value = "/editScreen-user")
public class EditUser extends HttpServlet {

    Dbconnection dbconnection;
    UserDaoImpl userDao;
    public void init(){
        this.userDao = new UserDaoImpl();
        this.dbconnection = new Dbconnection();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.selectUserById(id, dbconnection);
        Cookie cookie = new Cookie("id", String.valueOf(id));
        res.addCookie(cookie);

        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req , res);
    }
}
