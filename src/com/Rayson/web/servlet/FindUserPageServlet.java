package com.Rayson.web.servlet;

import com.Rayson.domain.PageBean;
import com.Rayson.domain.User;
import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserPageServlet")
public class FindUserPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        UserService userService = new UserServiceImpl();
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        //条件参数

        Map<String, String[]> condition = req.getParameterMap();
        PageBean<User> pb = userService.findUserByPage(currentPage, rows, condition);

        req.setAttribute("pb", pb);
        req.setAttribute("condition", condition);

        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
