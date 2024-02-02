package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import other.EmailSender;
import other.Otp;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordController {
    public JFXButton backButton;
    public JFXButton sendBtn;
    public JFXTextField emailTxt;
    public JFXButton verifyBtn;
    public JFXTextField codeTxt;

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
    private static String otp="";

    public void SendButtonOnAction(ActionEvent actionEvent) {
           UserDto userDto = userBo.searchUser(emailTxt.getText());

        if(userDto != null) {
//            if (!emailTxt.getText().isEmpty()) {
                String to = emailTxt.getText();
                String subject = "OTP verification";

                String body = Otp.generateOtp(to);
                EmailSender.sendEmail(to, subject, body);
                otp = body;


        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid  email").show();
        }
    }

    public void verifyBtnOnAction(ActionEvent actionEvent) {
        if(otp.equals(codeTxt.getText())){
            loardResetPasswordForm();
        }else {
            new Alert(Alert.AlertType.ERROR, "Try again").show();
        }
    }

private void loardResetPasswordForm() {
    Stage stage = (Stage) verifyBtn.getScene().getWindow();
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VerificationNewPassword.fxml"));
        Scene scene = new Scene(loader.load());

       VerificationNewPasswordContoller verificationController = loader.getController();

        verificationController.setEmail(emailTxt.getText());

        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

}
