package com.Rayson.web.servlet;

import com.Rayson.domain.User;
import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);

    }



    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1 . 调用  完成查询

        UserService service = new UserServiceImpl();

        List<User> users = service.findAll();

        // 2 .将数据存入req中

        req.setAttribute("users", users);
        req.setAttribute("hh","hh");

        // 3. 转发到list.jsp页面中

        req.getRequestDispatcher("/list.jsp").forward(req, resp);

    }
}
