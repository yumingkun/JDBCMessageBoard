package com.jdbc.servlet;

import com.jdbc.bean.Message;
import com.jdbc.bean.User;
import com.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 留言处理
 */
@WebServlet(name = "AddMessageServlet",urlPatterns = {"/addMessagePrompt.do","/addMessage.do"})
public class AddMessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService=new MessageService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathName=request.getServletPath();//获取url
        if(Objects.equals("/addMessage.do",pathName)){//点击添加post过来的数据
            //根据用户是否登录，来做选择
            User user=(User)request.getSession().getAttribute("user");
            if (null==user){//如果没有登录，可以看留言但是不能写留言
                request.getRequestDispatcher("/message/list.do").forward(request,response);
            }else {//如果登录了，拿到前端传来的数据
                String title=request.getParameter("title");
                String content=request.getParameter("content");
//                System.out.println(title+content);
                boolean result=messageService.addMessage(new Message((int)user.getId(),user.getName(),title,content));//进行数据保存数据库
                if (result){
                    request.getRequestDispatcher("/message/list.do").forward(request,response);
                }else {
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
                }
            }

        }else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName=request.getServletPath();//获取url

        System.out.println("进入留言");
        if (Objects.equals("/addMessagePrompt.do",pathName)){// 点击留言进入的页面
            request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request,response);

        }else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
        }



    }
}
