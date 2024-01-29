package com.code.empcrud.empcrud.web;

import com.code.empcrud.empcrud.dao.UserDao;
import com.code.empcrud.empcrud.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController extends HttpServlet {

    private UserDao userDao;
    public void  init(){
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException{
        String action = req.getServletPath();

        switch (action){
            case "/new":
                showNewForm(req, res);
                break;
            case "/insert":
                insertUser(req, res);
                break;
            case "/delete":
                deleteUser(req, res);
                break;
            case "/update":
                updateUser(req, res);
                break;
            default:
                listUser(req, res);
                break;

        }
    }


    private void listUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException{
        List<User> listUser = userDao.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, res);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws  IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDao.selectUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, res);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String name =  req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User newUser = new User(name , email, country);
        userDao.insertUser(newUser);
        res.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse res) throws  IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        String name =  req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User updatedUser = new User(id, name , email, country);
        userDao.updateUser(updatedUser);
        res.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse res) throws  IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        userDao.deleteUser(id);
        res.sendRedirect("list");

    }
}
