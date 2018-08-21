package com.jdbc.dao;

import com.jdbc.bean.User;
import com.jdbc.common.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * userdao 判断前端传来的登录信息
 */
public class UserDAO {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 成功返回数据库用户实体 失败返回null
     */
    public User login(String username,String password){
        Connection conn=ConnectUtil.getConnection();
        String sql="select * from user where username= ? and password=?";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        User user=null;

        try {
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs=stmt.executeQuery();
            //如果有这个用户，则封装并返回这个用户信息
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("登录失败");
            e.printStackTrace();
            return null;
        }finally {
            ConnectUtil.release(rs,stmt,conn);
        }


        return  user;
    }
}
