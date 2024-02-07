package controller;

import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;

import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {

    public JFXTextField orderIdTxt;
    public Label lblOrderId;
    public Label lblCustId;
    public JFXComboBox cmbCode;

    public JFXComboBox cmbCategory;
    @FXML
    private Label lblDate;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField NumberTxt;





    @FXML
    private JFXTextField DescriptionTxt;

    @FXML
    private JFXTextField partsTxt;

    @FXML
    private JFXTextField priceTxt;

private OrderBo orderBo=new OrderBoImpl();
private CustomerBo customerBo=new CustomerBoImpl();

private ItemBo itemBo=new ItemBoImpl();




    public void initialize(){
        calculateDate();
        setOrderId();
        setCustomerId();
        loadItemCodes();
        loadCatagory();
    }

    private void loadCatagory() {
       cmbCategory.setItems(itemBo.getItemType());
    }

    private void loadItemCodes() {
        try {
            List<ItemDto> allItems = itemBo.allItems();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemDto dto:allItems) {
                list.add(dto.getItemCode());
            }
            cmbCode.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCustomerId() {
        try {
            lblCustId.setText(customerBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void calculateDate() {
        LocalDate localDate=LocalDate.now();
        lblDate.setText(String.valueOf(localDate));
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backBtn.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void placeButtonOnAction(ActionEvent event) {
        if (validateNumber()==true) {
            List<OrderDetailsDto> list = new ArrayList<>();
            list.add(new OrderDetailsDto(
                    lblOrderId.getText(),
                    cmbCode.getValue().toString(),
                    Double.parseDouble(priceTxt.getText())

            ));

            try {
                Boolean isSaved = orderBo.saveOrder(new OrderDto(
                        lblOrderId.getText(),
                        cmbCode.getValue().toString(),
                        cmbCategory.getValue().toString(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
                        DescriptionTxt.getText(),
                        "pending",
                        lblCustId.getText(),
                        list
                ));

                if (isSaved) {

                    customerBo.saveCustomer(new CustomerDto(
                            lblCustId.getText(),
                            NumberTxt.getText(),
                            nameTxt.getText(),
                            emailTxt.getText().toString()
                    ));

                    new Alert(Alert.AlertType.INFORMATION, "Order Placed successfully!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Invalid Number").show();
        }
    }

    private void clearFields() {
        nameTxt.clear();
        NumberTxt.clear();
        emailTxt.clear();
        DescriptionTxt.clear();
        partsTxt.clear();
        priceTxt.clear();
    }
    private boolean validateNumber(){
        String number = NumberTxt.getText();
        if (!number.matches("\\d+")) {
            return false;
        }
        if (number.length() != 10 || !number.startsWith("0")) {
            return false;
        }
        return true;


    }

}
