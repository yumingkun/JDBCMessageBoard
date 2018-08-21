package com.jdbc.servlet;

import com.jdbc.bean.User;
import com.jdbc.service.UserService;
import org.omg.CORBA.Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

//查看和修改用户信息
@WebServlet(name = "UserServlet",urlPatterns = {"/userInfo.do","/editUserPrompt.do","/editUser.do"})
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        点击修改post调用get
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathName=request.getServletPath();//得到相对项目的url
//        http://localhost:8080/JDBCMessageBoard/userInfo.do

//        点击"我的信息"
        if (Objects.equals("/userInfo.do",pathName)){
            // 从会话中取出用户信息(登录的时候放进去的)，也可用过滤
            User user=(User) request.getSession().getAttribute("user");
            request.setAttribute("user",user);
            request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request,response);

//        点击"修改"
        }else if(Objects.equals("/editUserPrompt.do",pathName)){
//            1：从session中获取user ,其实就是和上一个一样
//            2：可扩展的方式，根据页面ID从数据库中查询
            Long id=Long.valueOf(request.getParameter("id"));
            User user=userService.getUserId(id);
            if (null!=user){
                request.setAttribute("user",user);
                request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request,response);
            }
//        点击""保存
        }else if(Objects.equals("/editUser.do",pathName)){
            Long id=Long.valueOf(request.getParameter("id"));
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String realName=request.getParameter("realName");
            String birthday=request.getParameter("birthday");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            User user=new User();
            user.setId(id);
            user.setName(name);
            user.setPassword(password);
            user.setRealName(realName);
            try {
                user.setBirthday(new SimpleDateFormat("yyy-MM-dd").parse(birthday));
            } catch (ParseException e) {
                System.out.println("格式化日期字段失败");
                e.printStackTrace();
            }
            user.setPhone(phone);
            user.setAddress(address);
//            调用更新
            boolean result=userService.updateUser(user);
//            用户更新之后成功或失败
            if (result){//更新成功之后
                request.getSession().setAttribute("user",user);
                request.setAttribute("user",user);
                request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request,response);
            }else {//更新失败之后
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
            }

        }else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
        }

    }
}
