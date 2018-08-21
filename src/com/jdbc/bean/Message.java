package com.jdbc.bean;

import java.util.Date;

//消息实体类domain
public class Message {
    private  int id;
    private int userId;
    private String userName;
    private  String title;
    private String content;
    private Date createTime;

    public Message(int id, int userId, String username, String title, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.userName = username;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Message(int userId, String username, String title, String content) {
        this.userId = userId;
        this.userName = username;
        this.title = title;
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
