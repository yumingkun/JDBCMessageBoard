package com.jdbc.dao;

import com.jdbc.bean.Message;
import com.jdbc.common.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//消息DAO
public class MessageDAO {


    /**
     * 分页查询全部留言
     * @param page page当前页码
     * @param pageSize 每页记录数
     * @return
     * @throws Exception
     */

    public  List<Message> getMessageList(int page ,int pageSize)  {

        Connection conn=ConnectUtil.getConnection();

        String sql="select * from message order by create_time desc limit ?,?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectUtil.release(rs, stmt, conn);
        }
        return messages;
    }



    /**
     * 计算所有留言数量
     * @return
     * @throws Exception
     */
    public  int countMessages() {
        Connection conn = ConnectUtil.getConnection();
        String sql = "select count(*) total from message";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectUtil.release(rs, stmt, conn);
        }
        return 0;
    }
}
