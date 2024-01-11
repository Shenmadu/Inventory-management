package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PlaceOrderFormController {

    public Label lblDate;
    public JFXButton backBtn;

    public void initialize(){
        calculateDate();
    }

    private void calculateDate() {
        LocalDate localDate=LocalDate.now();
        lblDate.setText(String.valueOf(localDate));
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
