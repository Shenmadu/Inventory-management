package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class OrderDetailFormController {
    public JFXButton backBtn;
    public JFXTextField IdTxt;
    public JFXTextField categoryTxt;
    public JFXTextField dateTxt;
    public JFXTextField codeTxt;
    public JFXTextField statusTxt;
    public JFXButton searchBtn;

    OrderDao orders=new OrderDaoImpl();
    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReportUser.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        try {
            OrderDto orderDto = orders.searchOrder(IdTxt.getText());

            if (orderDto!=null){
                codeTxt.setText(orderDto.getDescription());
                categoryTxt.setText(orderDto.getItem());
                statusTxt.setText(orderDto.getStatus());
                dateTxt.setText(orderDto.getCatagory());

            }else {
                new Alert(Alert.AlertType.INFORMATION, "Invalid Order Id!").show();
                IdTxt.clear();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
