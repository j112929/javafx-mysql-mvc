//package test.java;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.util.DBConnect;



public class TestSQl {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        String DBName = "project";
        String username = "root";
        String password = "root";



        Connection con = DBConnect.connectDB();

        String sql = "INSERT INTO user VALUES(NULL,?,?,md5('" + password + "'));";  //可以md5

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);

        pstm.executeUpdate();


//        PreparedStatement pstm = (PreparedStatement) con.;
//        pstm.executeUpdate("INSERT INTO user VALUES(NULL," + username + "," + password + ",md5('" + password + "'));");

        //re.last();

//        while(re.next()) {
//            System.out.println("hello");
//            System.out.println(re.getString(2));
//        }


    }

}
