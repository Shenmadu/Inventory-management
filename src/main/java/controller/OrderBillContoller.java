package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.OrderDao;
import dao.custom.OrderDetailsDao;
import dao.custom.impl.OrderDaoImpl;
import dao.custom.impl.OrderDetailsDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class OrderBillContoller {
    public JFXButton backButton;
    public Label lblNetCost;
    public JFXTextField repairCostTxt;
    public Label lblOrderId;
    public Label lblItemCode;
    public Label lblPartCost;

    private String orderId;
    private String itemcode;
    OrderDetailsDao dao=new OrderDetailsDaoImpl();

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public void initialize(){
        lblOrderId.setText(orderId);
        lblItemCode.setText(itemcode);
   }


    private void loardPartcost() {
        try {
            lblPartCost.setText(dao.getOrder(orderId,itemcode).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void calculateCost() {
       if(repairCostTxt.getText() != null){
           lblNetCost.setText(repairCostTxt.getText());

       }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calculateBtnOnAction(ActionEvent actionEvent) {
        calculateCost();
    }
}
