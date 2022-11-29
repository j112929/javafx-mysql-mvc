package com.demo.util;

import java.sql.*;

public class DBConnect {
    //"project", "root", "root"
    static String DBName = "510labs";
    static String user = "db510";
    static String password = "db510";

    public static Connection connectDB() throws SQLException, ClassNotFoundException {

        Connection con = null;

        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://www.papademas.net:3307/" + DBName + "?autoReconnect=true&useSSL=false";
//                "jdbc:mysql://localhost:3306/" + DBName + "?useSSL=false&characterEncoding=utf-8";
        con = DriverManager.getConnection(url, user, password);
        return con;
    }
    public static ResultSet executeQuery(String sql){
        try {
            Connection con = DBConnect.connectDB();
            Statement stm = con.createStatement();
            return stm.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static int executeUpdate(String sql){
        try {
            Connection con = DBConnect.connectDB();
            Statement stm = con.createStatement();
            return stm.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
