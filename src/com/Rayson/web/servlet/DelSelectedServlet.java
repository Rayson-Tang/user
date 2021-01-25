package com.Rayson.web.servlet;

import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String[] uids = req.getParameterValues("uid");
        UserService userService = new UserServiceImpl();
        userService.deleteSelectedUser(uids);
        resp.sendRedirect(req.getContextPath() + "userListServlet");
    }
}
