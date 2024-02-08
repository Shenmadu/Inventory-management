package controller;

import bo.custom.OrderBo;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;
import dto.tm.InventoryTm;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InventoryController {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXComboBox<?> catagoryDrp;

    @FXML
    private JFXTextField statusTxt;

    @FXML
    private JFXTextField itemCodeTxt;

    @FXML
    private JFXTreeTableView<InventoryTm> PendingTbl;

    @FXML
    private TreeTableColumn<?, ?> colPendCode;

    @FXML
    private TreeTableColumn<?, ?> colPendCatagory;

    @FXML
    private TreeTableColumn<?, ?> colPendOption;

    @FXML
    private JFXTreeTableView<?> pending2Tbl;

    @FXML
    private TreeTableColumn<?, ?> colpend2Code;

    @FXML
    private TreeTableColumn<?, ?> colpend2Catagory;

    @FXML
    private TreeTableColumn<?, ?> colpend2Option;

    @FXML
    private JFXTreeTableView<InventoryTm> processingTbl;

    @FXML
    private TreeTableColumn<?, ?> colProCode;

    @FXML
    private TreeTableColumn<?, ?> colProCatagory;

    @FXML
    private TreeTableColumn<?, ?> colProOption;

    @FXML
    private JFXTreeTableView<InventoryTm> completedTbl;

    @FXML
    private TreeTableColumn<?, ?> colComCode;

    @FXML
    private TreeTableColumn<?, ?> colComCatagory;

    @FXML
    private TreeTableColumn<?, ?> colComOption;

    OrderBo orderBo=new OrderBoImpl();
    public void initialize() {
        colPendCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colPendCatagory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colPendOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        colProCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colProCatagory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colProOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        colComCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colComCatagory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colComOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        loardPendingTable("pending");
        loardProcessingTable("Processing");
        loardCompletedTable("Completed");
        

    }

    private void loardCompletedTable(String completed) {
        ObservableList<InventoryTm> tmlist = FXCollections.observableArrayList();
        List<OrderDto> dtoList = null;
        dtoList = orderBo.getOrdersByStatus(completed);
        for (OrderDto dto:dtoList) {
            JFXButton btn = new JFXButton("Delete");
            btn.setStyle("-fx-background-color: red;");
            InventoryTm inventoryTm=new InventoryTm(
                    dto.getItem(),
                    dto.getStatus(),
                    dto.getCatagory(),
                    dto.getDate(),
                    btn

            );
            btn.setOnAction(actionEvent -> {
                deleteOrder(dto.getOrderId());
            });

            tmlist.add(inventoryTm);
            RecursiveTreeItem<InventoryTm> treeItem=new RecursiveTreeItem<>(tmlist, RecursiveTreeObject::getChildren);
            completedTbl.setRoot(treeItem);
            completedTbl.setShowRoot(false);
        }

    }

    private void loardProcessingTable(String status) {
        ObservableList<InventoryTm> tmlist = FXCollections.observableArrayList();
        List<OrderDto> dtoList = null;
        dtoList = orderBo.getOrdersByStatus(status);
        for (OrderDto dto:dtoList) {
            JFXButton btn = new JFXButton("Delete");
            btn.setStyle("-fx-background-color: red;");
            InventoryTm inventoryTm=new InventoryTm(
                    dto.getItem(),
                    dto.getStatus(),
                    dto.getCatagory(),
                    dto.getDate(),
                    btn

            );
            btn.setOnAction(actionEvent -> {
                deleteOrder(dto.getOrderId());
            });

            tmlist.add(inventoryTm);
            RecursiveTreeItem<InventoryTm> treeItem=new RecursiveTreeItem<>(tmlist, RecursiveTreeObject::getChildren);
            processingTbl.setRoot(treeItem);
            processingTbl.setShowRoot(false);
        }
    }

    private void loardPendingTable(String status) {
        ObservableList<InventoryTm> tmlist = FXCollections.observableArrayList();
        List<OrderDto> dtoList = null;
        dtoList = orderBo.getOrdersByStatus(status);
        for (OrderDto dto:dtoList) {
            JFXButton btn = new JFXButton("Delete");
            btn.setStyle("-fx-background-color: red;");
            InventoryTm inventoryTm=new InventoryTm(
                    dto.getItem(),
                    dto.getStatus(),
                    dto.getCatagory(),
                    dto.getDate(),
                    btn

            );
            btn.setOnAction(actionEvent -> {
                deleteOrder(dto.getOrderId());
            });

            tmlist.add(inventoryTm);
            RecursiveTreeItem<InventoryTm> treeItem=new RecursiveTreeItem<>(tmlist, RecursiveTreeObject::getChildren);
            PendingTbl.setRoot(treeItem);
            PendingTbl.setShowRoot(false);
        }


    }

    private void deleteOrder(String item) {
        try {
            boolean isDeleted = orderBo.deleteOrder(item);

            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Order Deleted!").show();


            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }



    @FXML
    void addBtnOnAction(ActionEvent event) {

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

}


