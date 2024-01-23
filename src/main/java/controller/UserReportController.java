package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserReportController {

    public JFXButton backButton;
    public JFXButton searchOrderBtn;
    public JFXButton allCustomerBtn;
    public JFXButton searchCustomerBtn;

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void allCustomerBtnOnAction(ActionEvent actionEvent) {
    }

    public void searchCustomerBtnOnAction(ActionEvent actionEvent) {
    }
}
