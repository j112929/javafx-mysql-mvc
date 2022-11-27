package com.demo.dao;

import com.demo.dto.UserBalanceDTO;
import com.demo.util.DBConnect;
import com.demo.util.PropertiesUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class BalanceDao {
    public ObservableList<UserBalanceDTO> list(){
        String sql ="select b.id,u.username,b.balance from user u right join balance b on u.id=b.user_id";
        if(!PropertiesUtil.get("user_name").equals("admin")){
            sql+=" where u.id="+PropertiesUtil.get("user_id");
        }
        ResultSet resultSet = DBConnect.executeQuery(sql);
        ObservableList<UserBalanceDTO> userBalanceDTOS = FXCollections.observableArrayList();
        try {
            while (resultSet.next()){
                UserBalanceDTO userBalanceDTO = new UserBalanceDTO();
                userBalanceDTO.setId(resultSet.getInt("id"));
                userBalanceDTO.setUsername(resultSet.getString("username"));
                userBalanceDTO.setBalance(resultSet.getString("balance"));
                userBalanceDTOS.add(userBalanceDTO);
            }
            return userBalanceDTOS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return userBalanceDTOS;
    }
}
