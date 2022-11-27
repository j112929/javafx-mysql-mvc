package com.demo.dao;

import com.demo.util.DBConnect;

import java.sql.ResultSet;

public class UserDao {
    public String getUserName(int id){
        ResultSet resultSet = DBConnect.executeQuery("select username from user where id=" + id);
        try {
            if(!resultSet.next()) return "";
            return resultSet.getString("username");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public int getId(String username){
        ResultSet resultSet = DBConnect.executeQuery("select id from user where username=" + username);
        try {
            if(!resultSet.next()) return 0;
            return resultSet.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
