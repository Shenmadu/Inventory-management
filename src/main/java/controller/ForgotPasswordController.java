package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.EmailSender;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPasswordController {
    public JFXButton backButton;
    public JFXButton sendBtn;
    public JFXTextField emailTxt;

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void SendButtonOnAction(ActionEvent actionEvent) {
        String to = emailTxt.getText();
        String subject = "Test Email";
        String body = "This is a test email from JavaFX.";


        EmailSender.sendEmail(to, subject, body);


        loadVerifyPage();
    }

    private void loadVerifyPage() {
        Stage stage = (Stage) sendBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Verification.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
