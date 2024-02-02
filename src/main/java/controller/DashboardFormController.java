package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.UserDto;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    public JFXPasswordField passwordTxt;
    public JFXTextField userNameTxt;
    public JFXRadioButton user;
    public JFXRadioButton admin;
    public JFXButton forgotPwd;
    public JFXButton lblTime;
    public JFXButton registerBtn;

    UserBo userBo=new UserBoImpl();
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
        if (user.isSelected()) {
            try {
                String enteredUserName = userNameTxt.getText().toString();
                String enteredPassword = passwordTxt.getText().toString();

                UserDto userDto = userBo.getUser(enteredUserName);

                if (userDto != null && userDto.getPassword().equals(enteredPassword) && userDto.getType().equals("User") ) {
                    Stage stage=(Stage) user.getScene().getWindow();
               try {
                   stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
                   stage.setTitle("User");
                   stage.show();
                   stage.setResizable(true);
               } catch (IOException e) {
                   e.printStackTrace();
               }

                } else {
                    new Alert(Alert.AlertType.ERROR, "incorrect userName or Password").show();
                }
            } catch (RuntimeException e) {

                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if(admin.isSelected()){
            String enteredUserName = userNameTxt.getText().toString();
            String enteredPassword = passwordTxt.getText().toString();

            try {
                UserDto userDto = userBo.getUser(enteredUserName);
                if (userDto != null && userDto.getPassword().equals(enteredPassword) && userDto.getType().equals("Admin")) {
                    Stage stage=(Stage) user.getScene().getWindow();
               try {
                   stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
                   stage.setTitle("Admin");
                   stage.show();
                   stage.setResizable(true);
               } catch (IOException e) {
                   e.printStackTrace();
               }

                } else {
                    new Alert(Alert.AlertType.ERROR, "incorrect userName or Password").show();
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }else{
            new Alert(Alert.AlertType.ERROR, "Please select your account type").show();
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

    public void registerBtnOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) registerBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreateAccountForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
