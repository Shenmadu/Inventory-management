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

public class CreateAccountFormController {
    public JFXButton backButton;
    public JFXButton createBtn;
    public JFXComboBox cmbCategory;
    public JFXTextField createPwdtxt;
    public JFXTextField retypePwdTxt;
    public JFXTextField emailTxt;

    UserBo userBo=new UserBoImpl();

    public void initialize(){
        setUserType();
    }

    private void setUserType(){
       cmbCategory.setItems(userBo.getUserType());
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBtnOnAction(ActionEvent actionEvent) {
        if (createPwdtxt.getText().equals(retypePwdTxt.getText())){
            try {
                Boolean saved = userBo.saveUser(new UserDto(
                        emailTxt.getText(),
                        retypePwdTxt.getText(),
                        cmbCategory.getValue().toString()
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
    }else {
            new Alert(Alert.AlertType.INFORMATION, "Not match your password").show();
        }
    }
    private void clearFields() {
        emailTxt.clear();
        createPwdtxt.clear();
        retypePwdTxt.clear();
    }
}
