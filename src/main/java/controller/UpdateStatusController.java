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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateStatusController {

    public JFXTextField idTxt;
    public JFXTextField codeTxt;
    public JFXTextField statusTxt;

    public JFXButton backButton;
    public JFXTextField dateTxt;
    public JFXButton updateBtn;
    public JFXButton addPartsBtn;
    OrderDao orderDao=new OrderDaoImpl();

    public void initialize(){
        addPartsBtn.setVisible(false);
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

    public void searchBtnOnAction(ActionEvent actionEvent) {
        try {
            OrderDto orderDto = orderDao.searchOrder(idTxt.getText());

            if (orderDto!=null){
                codeTxt.setText(orderDto.getItem());
                statusTxt.setText(orderDto.getStatus());
                dateTxt.setText(orderDto.getDate());

            }else {
                new Alert(Alert.AlertType.INFORMATION, "Invalid Order Id!").show();
                idTxt.clear();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(statusTxt.getText().equals("processing")){
            addPartsBtn.setVisible(true);
        }

    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        if(statusTxt.getText().equals("pending")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Set status Processing");
            alert.show();

        } else if (statusTxt.getText().equals("processing")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Set status Completed");
            alert.show();

        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Set status Closed");
            alert.show();

        }

    }

    public void addPartsBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) addPartsBtn.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddParts.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
