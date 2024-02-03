package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import dao.custom.OrderDetailsDao;
import dao.custom.impl.OrderDetailsDaoImpl;
import dto.tm.PartsTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddPartsContoller {

    @FXML
    private JFXTreeTableView<PartsTm> partsTbl;
    @FXML
    private JFXButton backButton;

    @FXML
    private JFXTextField nametxt;

    @FXML
    private JFXTextField qtyTxt;

    @FXML
    private JFXTextField unitPriceTxt;

    @FXML
    private TreeTableColumn<?, ?> colID;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colAmount;

    @FXML
    private Label lblTotal;

    private String orderId;
    private String itemcode;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
    List<PartsTm> partList=new ArrayList<>();

    OrderDetailsDao detailsDao=new OrderDetailsDaoImpl();

    public void initialize() {
        colName.setCellValueFactory(new TreeItemPropertyValueFactory<>("partName"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
    }


    @FXML
    void addbtnOnAction(ActionEvent event) {
        ObservableList<PartsTm> tmList = FXCollections.observableArrayList();
        partList.add(new PartsTm(
                nametxt.getText(),
                Integer.parseInt(qtyTxt.getText()),
                Double.parseDouble(unitPriceTxt.getText()))
        );
        tmList.addAll(partList);
        TreeItem<PartsTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        partsTbl.setRoot(treeItem);
        partsTbl.setShowRoot(false);
        clearText();
    }
    private void clearText(){
        nametxt.clear();
        qtyTxt.clear();
        unitPriceTxt.clear();

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UpdateStatus.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void confirmBtnOnAction(ActionEvent actionEvent) {
        double total = 0.0;
        for (PartsTm partsTm : partList) {
            total += partsTm.getAmount();
        }
        lblTotal.setText("Total: " + total);

        String id =orderId;
        String code =itemcode;
        double newPrice = total;

        try {
            detailsDao.updatePartCost(id,code,newPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
