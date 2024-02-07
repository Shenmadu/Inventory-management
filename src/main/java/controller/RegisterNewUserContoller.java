package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.CustomerDto;
import dto.UserDto;
import dto.tm.UserDtoTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterNewUserContoller {
    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField pwdTxt;

    @FXML
    private JFXComboBox<String> cmbType;
    @FXML
    private JFXTreeTableView<UserDtoTm> tblUsers;

    @FXML
    private TreeTableColumn<?, ?> colType;

    @FXML
    private TreeTableColumn<?, ?> colEmail;

    @FXML
    private TreeTableColumn<?, ?> colPwd;
    @FXML
    private TreeTableColumn<?, ?> coloption;
UserBo userBo=new UserBoImpl();
    public void initialize(){
        colType.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colPwd.setCellValueFactory(new TreeItemPropertyValueFactory<>("password"));
        coloption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        setUserType();
        loardUsersTable();
    }

    private void setUserType(){
        cmbType.setItems(userBo.getUserType());
    }

    private void loardUsersTable() {
        ObservableList<UserDtoTm> tmList = FXCollections.observableArrayList();

        List<UserDto> dtoList = null;
        try {
            dtoList = userBo.allUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (UserDto dto:dtoList) {
                JFXButton btn = new JFXButton("Delete");
                btn.setStyle("-fx-background-color: red;");

                UserDtoTm u = new UserDtoTm(
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getType(),
                        btn
                );
            btn.setOnAction(actionEvent -> {
                deleteUser(u.getEmail());
            });

                tmList.addAll(u);
            }
           TreeItem<UserDtoTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
           tblUsers.setRoot(treeItem);
           tblUsers.setShowRoot(false);

    }

    private void deleteUser(String email) {
        try {
            boolean isDeleted = userBo.deleteUser(email);

            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"User Account Deleted!").show();
                loardUsersTable();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void createBtnOnAction(ActionEvent actionEvent) {
            try {
                String hashedPassword = BCrypt.hashpw(pwdTxt.getText(), BCrypt.gensalt());
                Boolean saved = userBo.saveUser(new UserDto(
                        emailTxt.getText(),
                        hashedPassword,
                        cmbType.getValue().toString()
                ));
                if (saved) {
                    new Alert(Alert.AlertType.INFORMATION, "Register succesfull!").show();
                    clearFields();
                    loardUsersTable();
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

