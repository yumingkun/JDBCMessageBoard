package com.jdbc.servlet;

import com.jdbc.bean.Message;
import com.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MessageListServletServlet",urlPatterns="/message/list.do")

public class MessageListServletServlet extends HttpServlet {
    private MessageService messageService;
    @Override
    public void init() throws ServletException {
        super.init();
        messageService=new MessageService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageStr=request.getParameter("page");//获得请求的页码
        int page=1;//页码默认为1
        if(pageStr!=null && (!"".equals(pageStr))){
            page=Integer.parseInt(pageStr);//获得数字页码
        }

        //根据获取前端传过来的page进行分页查询
        List<Message> messages =messageService.getMessages(page,5);//分页查询全部留言
        int count=messageService.countMessage();//获取全部消息数量

        int last=count%5 ==0? (count/ 5):((count/5)+1);//最后一页
        request.setAttribute("last",last);
        request.setAttribute("messages",messages);
        request.setAttribute("page",page);

        request.getRequestDispatcher("/WEB-INF/views/biz/message_list.jsp").forward(request,response);

    }

}

