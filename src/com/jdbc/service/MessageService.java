package com.jdbc.service;

import com.jdbc.bean.Message;
import com.jdbc.dao.MessageDAO;

import java.util.List;

//消息service
public class MessageService {
    private MessageDAO messageDAO;
    public MessageService(){
        messageDAO=new MessageDAO();
    }

    /**
     * 分页查询所有留言
     * @param page 当前页码
     * @param pageSize 每页数量
     * @return

     */
    public List<Message> getMessages(int page,int pageSize)   {
        // 得到messages
        return messageDAO.getMessageList(page,pageSize);
    }

    /**
     * 计算所有留言数量
     * @return

     */
    public  int countMessage() {

        return messageDAO.countMessages();
    }
}
