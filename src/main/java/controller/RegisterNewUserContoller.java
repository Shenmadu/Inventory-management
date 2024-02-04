package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterNewUserContoller {
    public JFXButton backButton;
    public JFXButton createBtn;
    public JFXTextField emailTxt;
    public JFXTextField pwdTxt;
    public JFXComboBox cmbType;
UserBo userBo=new UserBoImpl();
    public void initialize(){
        setUserType();
    }

    private void setUserType(){
        cmbType.setItems(userBo.getUserType());
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBtnOnAction(ActionEvent actionEvent) {
            try {
                Boolean saved = userBo.saveUser(new UserDto(
                        emailTxt.getText(),
                        pwdTxt.getText(),
                        cmbType.getValue().toString()
                ));
                if (saved) {
                    new Alert(Alert.AlertType.INFORMATION, "Register succesfull!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    private void clearFields() {
        emailTxt.clear();
        pwdTxt.clear();

    }

}
