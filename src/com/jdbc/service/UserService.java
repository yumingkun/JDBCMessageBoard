package com.jdbc.service;

import com.jdbc.bean.User;
import com.jdbc.common.ConnectUtil;
import com.jdbc.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;
    public  UserService(){
        userDAO =new UserDAO();
    }
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 成功返回Dao
     */
    public User login(String username, String password){

        return  userDAO.login(username,password);
    }

    /**
     * 根据ID获取用户信息
     * @param id 前端传过来的id
     * @return
     */
    public User getUserId(Long id){
        return  userDAO.getUserId(id);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public  boolean updateUser(User user){
        return userDAO.updateUser(user);
    }



}
