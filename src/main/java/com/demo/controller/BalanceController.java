package com.demo.controller;

import com.demo.dao.BalanceDao;
import com.demo.dto.UserBalanceDTO;
import com.demo.model.Balance;
import com.demo.util.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kobinath
 */
public class BalanceController implements Initializable {

    int myIndex;
    int id;
//    @FXML
//    private Label label;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtBalance;
    @FXML
    private TableView<UserBalanceDTO> table;
    @FXML
    private TableColumn<UserBalanceDTO, String> IDColmn;
    @FXML
    private TableColumn<UserBalanceDTO, String> UserNameColmn;
    @FXML
    private TableColumn<UserBalanceDTO, String> BalanceColmn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    private BalanceDao balanceDao = new BalanceDao();

    @FXML
    void Add(ActionEvent event) {

        String username, balance;
        username = txtUserName.getText();
        balance = txtBalance.getText();
        try {
            ResultSet rs = DBConnect.executeQuery("select id from htt_user where username='" + username+"'");
            if(!rs.next()){
                return;
            }
            String userId = rs.getString(1);
            int count = DBConnect.executeUpdate("insert into htt_balance (id,user_id,balance) values (null,"+userId+","+balance+")");
            if(count ==0) return;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Balance Add");
            alert.setHeaderText("Balance Add");
            alert.setContentText("Record Added!");
            alert.showAndWait();

            table();

            txtUserName.setText("");
            txtBalance.setText("");
            txtUserName.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(BalanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void table() {
//        ObservableList<UserBalanceDTO> userBalanceDTOS = FXCollections.observableArrayList();
        try {
//            userBalanceDTOS = balanceDao.list();
            table.getItems().setAll(balanceDao.list());
        } catch (Exception ex) {
            Logger.getLogger(BalanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory(tv -> {
            TableRow<UserBalanceDTO> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtUserName.setText(table.getItems().get(myIndex).getUsername());
                    txtBalance.setText(table.getItems().get(myIndex).getBalance());
                }
            });
            return myRow;
        });


    }

    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));


        try {
//            pst = con.prepareStatement("delete from registation where id = ? ");
//            pst.setInt(1, id);
//            pst.executeUpdate();
            int cnt = DBConnect.executeUpdate("delete from htt_balance where id = "+id);
            if(cnt==0) return;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Balance Delete");

            alert.setHeaderText("Balance Delete");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();
        } catch (Exception ex) {
            Logger.getLogger(BalanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Update(ActionEvent event) {

        String username, balance;

        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

//        username = txtUserName.getText();
        balance = txtBalance.getText();
        try {

            DBConnect.executeUpdate("update htt_balance set balance = "+balance+" where id="+id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Balance Update");

            alert.setHeaderText("Balance Update");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            table();
        } catch (Exception ex) {
            Logger.getLogger(BalanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        IDColmn.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("id"));
        BalanceColmn.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("balance"));
        UserNameColmn.setCellValueFactory(new PropertyValueFactory<UserBalanceDTO, String>("username"));
////        List<UserBalanceDTO> list = parseUserList();
//        table.getItems().setAll();
//        table();
    }

}