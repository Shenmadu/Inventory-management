package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.ItemDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemController {

    public JFXButton AddBtn;
    public JFXButton removeBtn;
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



    private ItemBo itemBo=new ItemBoImpl();

    public void initialize() {
        colElectricCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colElectricName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));

        colElectroCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colElectroName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));


        loardElectricalItemTable();
        loardElectronicItemTable();
    }
    private void loardElectricalItemTable() {
        ObservableList<ItemDto> tmList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList = itemBo.getAllByCategory("Electrical");
            tmList.addAll(dtoList);

            TreeItem<ItemDto> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblElectricalItem.setRoot(treeItem);
            tblElectricalItem.setShowRoot(false);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void loardElectronicItemTable() {
        ObservableList<ItemDto> tmList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList = itemBo.getAllByCategory("Electronic");
            tmList.addAll(dtoList);

            TreeItem<ItemDto> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblElectronicItem.setRoot(treeItem);
            tblElectronicItem.setShowRoot(false);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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




    public void AddBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) AddBtn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBtnOnAction(ActionEvent actionEvent) {
    }
}
