package com.demo.controller;

import com.demo.dto.UserBalanceDTO;
import com.demo.util.DBConnect;
import com.demo.util.PropertiesUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class Balance1Controller implements Initializable {

    ObservableList<UserBalanceDTO> list = FXCollections.observableArrayList(
            new UserBalanceDTO()
    );
    @FXML
    private TableView<UserBalanceDTO> tableView;
    @FXML
    private TableColumn<UserBalanceDTO, String> id;
    @FXML
    private TableColumn<UserBalanceDTO, String> balance;
    @FXML
    private TableColumn<UserBalanceDTO, String> username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("id"));
        balance.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("balance"));
        username.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("username"));
        List<UserBalanceDTO> list = parseUserList();
        tableView.getItems().setAll(list);
        AtomicInteger selectedIndex = new AtomicInteger(-1);
        AtomicInteger id = new AtomicInteger();
        TextField nameText = new TextField();
        tableView.setOnMouseClicked(e -> {
            String balance = tableView.getSelectionModel().getSelectedItem().getBalance();
            id.set(tableView.getSelectionModel().getSelectedItem().getId());
            nameText.setText(balance);
        });
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            UserBalanceDTO userBalanceDTO = tableView.getSelectionModel().getSelectedItem();
            add(userBalanceDTO);
        });
        Button updateButton = new Button("update");
        updateButton.setOnAction(e -> {
            UserBalanceDTO userBalanceDTO = tableView.getSelectionModel().getSelectedItem();
            update(userBalanceDTO);
        });
        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(e -> {
            UserBalanceDTO userBalanceDTO = tableView.getSelectionModel().getSelectedItem();
            delete(userBalanceDTO);
        });


    }

    private List<UserBalanceDTO> parseUserList() {
        // parse and construct balance datamodel list by looping your ResultSet rs
        // and return the list
        try {

            String qname = "SELECT b.id, u.id,u.username,b.balance from user u left join balance b on u.id = b.user_id";
            if (!PropertiesUtil.get("user_name").equals("admin")) {
                qname += " where u.id=" + PropertiesUtil.get("user_id");
            }
            ResultSet re = DBConnect.executeQuery(qname);
            List<UserBalanceDTO> list = new ArrayList<>();
            while (re.next()) {
                UserBalanceDTO userBalanceDTO = new UserBalanceDTO();
                userBalanceDTO.setId(re.getInt(1));
//                userBalanceDTO.setUser_id(re.getInt(2));
                userBalanceDTO.setUsername(re.getString(3));
                userBalanceDTO.setBalance(re.getString(4));
                list.add(userBalanceDTO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(UserBalanceDTO userBalanceDTO) {
        String qname = "insert into balance value(null, " + PropertiesUtil.get("user_id") + "," + userBalanceDTO.getBalance() + ")";
        int re = DBConnect.executeUpdate(qname);
        return re != 0;
    }
    public boolean update(UserBalanceDTO userBalanceDTO) {
        String qname = "update balance set balance="+ userBalanceDTO.getBalance() + " where id="+ userBalanceDTO.getId();
        int re = DBConnect.executeUpdate(qname);
        return re != 0;
    }
    public boolean delete(UserBalanceDTO userBalanceDTO) {
        String qname = "delete from balance where id="+ userBalanceDTO.getId();
        int re = DBConnect.executeUpdate(qname);
        return re != 0;
    }



}
