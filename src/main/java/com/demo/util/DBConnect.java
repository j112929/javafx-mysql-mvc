package com.demo.util;

import java.sql.*;

public class DBConnect {

    public static Connection connectDB(String DBName, String user, String password) throws SQLException, ClassNotFoundException {

        Connection con = null;

        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false&characterEncoding=utf-8";
        con = DriverManager.getConnection(url, user, password);
        return con;
    }
    public static ResultSet executeQuery(String sql){
        try {
            Connection con = DBConnect.connectDB("project", "root", "root");
            Statement stm = con.createStatement();
            return stm.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static int executeUpdate(String sql){
        try {
            Connection con = DBConnect.connectDB("project", "root", "root");
            Statement stm = con.createStatement();
            return stm.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
