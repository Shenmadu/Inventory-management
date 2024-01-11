package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    public JFXPasswordField passwordTxt;
    public JFXTextField userNameTxt;
    public JFXRadioButton user;
    public JFXRadioButton admin;
    public JFXButton forgotPwd;
    public JFXButton lblTime;
    public void initialize(){
        calculatetime();

    }

    private void calculatetime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



    public void logInButtonOnAction(ActionEvent actionEvent) {

       if(user.isSelected()){
           if ((userNameTxt.getText().equals("abc")) && passwordTxt.getText().equals("1234") ){


               Stage stage=(Stage) user.getScene().getWindow();
               try {
                   stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
                   stage.setTitle("User");
                   stage.show();
                   stage.setResizable(true);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }



       }else if(admin.isSelected()){

           if ((userNameTxt.getText().equals("aaa")) && passwordTxt.getText().equals("1111") ) {


               Stage stage=(Stage) user.getScene().getWindow();
               try {
                   stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
                   stage.setTitle("Admin");
                   stage.show();
                   stage.setResizable(true);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) forgotPwd.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgotPassword.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
