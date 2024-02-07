package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

public class VerificationNewPasswordContoller {

    public JFXButton backButton;
    public JFXButton summitBtn;
    public JFXPasswordField pwdText;
    public JFXPasswordField retypePwdTxt;


    private String email;
    public void setEmail(String email) {
        this.email = email;
    }


    UserBo userBo=new UserBoImpl();

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sumbitBtnOnAction(ActionEvent actionEvent) {
        try {
            String hashedPassword = BCrypt.hashpw(retypePwdTxt.getText(), BCrypt.gensalt());
            boolean updated = userBo.updatePassword(email, hashedPassword);
            if(updated){
                new Alert(Alert.AlertType.INFORMATION, "Change Password successfully!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
