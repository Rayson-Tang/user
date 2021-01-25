package com.Rayson.web.servlet;

import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.编码
        req.setCharacterEncoding("utf-8");
        //2.获取对象
        String id = req.getParameter("id");
        //3.封装对象



        //4.重定向
        UserService userService = new UserServiceImpl();
        userService.deleteUser(id);
        resp.sendRedirect(req.getContextPath() + "userListServlet");
    }
}
