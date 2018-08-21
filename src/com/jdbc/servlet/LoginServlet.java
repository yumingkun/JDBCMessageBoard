package com.jdbc.servlet;

import com.jdbc.bean.User;
import com.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService =new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  username=request.getParameter("username");
        String  password=request.getParameter("password");

        User user=userService.login(username,password);//如果验证成功，返回一个用户实体
        System.out.println(user);
        if (null!=user){
            System.out.println("成功");
            request.getSession().setAttribute("user",user);//登录成功，把用户信息放到会话里
            request.getRequestDispatcher("/message/list.do").forward(request,response);
        }else {
            System.out.println("失败");
            request.getRequestDispatcher("/WEB-INF/views/biz/test.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  只是单击登录，跳转到登录页面
        request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request,response);
    }
}
