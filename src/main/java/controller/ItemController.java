package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemController {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXTreeTableView<ItemDto> tblElectricalItem;

    @FXML
    private TreeTableColumn<?, ?> colElectricImg;

    @FXML
    private TreeTableColumn<?, ?> colElectricCode;

    @FXML
    private TreeTableColumn<?, ?> colElectricName;

    @FXML
    private JFXTreeTableView<ItemDto> tblElectronicItem;

    @FXML
    private TreeTableColumn<?, ?> colElectroImg;

    @FXML
    private TreeTableColumn<?, ?> colElectroCode;

    @FXML
    private TreeTableColumn<?, ?> colElectroName;

    @FXML
    private JFXButton electricAddBtn;

    @FXML
    private JFXButton electroAddBtn;

    public void initialize() {
        loardElectricalItemTable();
        loardElectronicItemTable();
    }
    private void loardElectricalItemTable() {


    }
    private void loardElectronicItemTable() {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void electricAddBtnOnAction(ActionEvent event) {

    }

    @FXML
    void electroAddBtnOnAction(ActionEvent event) {

    }

}
