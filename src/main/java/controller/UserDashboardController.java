package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardController {
    public JFXButton backButton;
    public JFXButton updateBtn;
    public JFXButton placeOrderBtn;
    public JFXButton updateStatusBtn;

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) updateBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UpdatePassword.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) updateBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatusBtnOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) updateStatusBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UpdateStatus.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
