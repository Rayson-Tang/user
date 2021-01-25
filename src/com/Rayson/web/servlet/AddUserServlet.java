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

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.编码
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");
        //3.封装对象
        User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setAddress(address);
        user.setQq(qq);
        user.setEmail(email);
        //4.调用方法
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        //5.重定向到list
//        resp.sendRedirect(req.getContextPath() + "/userListServlet");
        req.getRequestDispatcher("/userListServlet").forward(req,resp);
    }
}
