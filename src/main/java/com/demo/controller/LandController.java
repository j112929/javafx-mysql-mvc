package com.demo.controller;

import com.demo.util.DBConnect;
import com.demo.util.PropertiesUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LandController implements Initializable {

    @FXML
    private Button onload;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label prompt;


    @FXML
    public void loadIn() throws Exception {

        if (username.getText().equals("")) {
            prompt.setText("please input username");
        } else {
            if (password.getText().equals("")) {
                prompt.setText("please input password");
            } else {

                String qname = "SELECT username from user where username=" + "'" + username.getText() + "'";
                ResultSet re = DBConnect.executeQuery(qname);
                if (!re.next()) {
                    prompt.setText("user not exist");
                } else {
                    String qpwd = "SELECT id,password from user WHERE username = '" + username.getText() + "';";
                    ResultSet re2 = DBConnect.executeQuery(qpwd);

                    if (re2.next()) {
                        if (!re2.getString(2).equals(password.getText()))
                            prompt.setText("password is wrong");
                        else {
                            PropertiesUtil.put("user_id", re2.getString(1));
                            PropertiesUtil.put("user_name", username.getText());
                            new ChangeScene("balance.fxml");
                        }
                    }
                }
            }
        }

    }

    public void ToRegister() throws IOException {
        new ChangeScene("register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
