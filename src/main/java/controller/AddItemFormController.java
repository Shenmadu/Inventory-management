package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddItemFormController {
    public JFXButton backButton;
    public GridPane catagoryCmbBox;
    public JFXTextField itmCodeTxt;
    public JFXTextField itmNameTxt;
    public JFXTextField itmCatagoryTxt;
    public JFXComboBox cmbCat;

    ItemBo itemBo=new ItemBoImpl();

    public void initialize(){
        setItemType();
    }

    private void setItemType() {
       cmbCat.setItems(itemBo.getItemType());
    }


    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Item.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddButtonOnAction(ActionEvent actionEvent) {
        try {
            Boolean saved = itemBo.saveItem(new ItemDto(
                            itmCodeTxt.getText(),
                            cmbCat.getValue().toString(),
                            itmNameTxt.getText()

            ));
            if(saved){
                new Alert(Alert.AlertType.INFORMATION, "Item Added succesfull!").show();
                clearFields();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        itmCodeTxt.clear();
        itmNameTxt.clear();
    }
}
