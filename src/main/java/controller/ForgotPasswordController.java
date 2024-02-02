package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import other.EmailSender;
import other.Otp;

import java.io.IOException;

public class ForgotPasswordController {
    public JFXButton backButton;
    public JFXButton sendBtn;
    public JFXTextField emailTxt;
    public JFXButton verifyBtn;
    public JFXTextField codeTxt;

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
        if(!emailTxt.getText().isEmpty()) {
            String to = emailTxt.getText();
            String subject = "OTP verification";
//        String body = "This is a test email from JavaFX.";
            String body = Otp.generateOtp(to);
            EmailSender.sendEmail(to, subject, body);
            otp=body;

        }else{
            new Alert(Alert.AlertType.ERROR, "please enter your email").show();
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

        Stage stage=(Stage) verifyBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/VerificationNewPassword.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
