package com.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;

import java.io.IOException;

import java.sql.Connection;
import com.demo.util.DBConnect;

public class RegisterController  {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;

    @FXML
    private Label prompt;

    @FXML
    public void Register() throws Exception {
        if(!username.getText().equals("") && !password.getText().equals("") && !repassword.getText().equals("")) {
            String sql = "INSERT INTO htt_user VALUES(NULL,"+username.getText()+","+password.getText()+",md5('" + password.getText() + "'));";
//                    "INSERT INTO user VALUES(NULL,?,?,md5('" + password.getText() + "'));";
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, username.getText());
//            pstm.setString(2, password.getText());
//            pstm.executeUpdate();
            /*
                pstm.executeUpdate("truncate table user");
             */
            DBConnect.executeUpdate(sql);
            prompt.setText("register success");
        } else {
            if(username.getText().equals("")) {
                prompt.setText("please input username");
            } else{
                if(password.getText().equals("")) {
                    prompt.setText("please input password");
                } else {
                    prompt.setText("please confirm your password");
                }
            }
        }
        System.out.println(username.getText() + "  " + password.getText());
    }

    @FXML
    public void ReturnLand() throws IOException {
        new ChangeScene("land.fxml");
    }




}
