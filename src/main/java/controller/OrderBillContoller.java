package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderBillContoller {
    public JFXButton backButton;
    public Label lblNetCost;
    public JFXTextField repairCostTxt;

   public void initialize(){

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
