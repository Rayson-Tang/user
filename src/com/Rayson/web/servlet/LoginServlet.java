package com.Rayson.web.servlet;

import com.Rayson.domain.User;
import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String verifycode = req.getParameter("verifycode");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equals(verifycode)){
            req.setAttribute("login_msg", "验证码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        User login = userService.login(user);
        if (user != null){
            session.setAttribute("user",login);
            resp.sendRedirect("index.jsp");
        }
        else{
            req.setAttribute("login_msg", "用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
