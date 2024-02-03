package controller;

import bo.custom.CustomerBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;

import dto.OrderDetailsDto;
import dto.OrderDto;
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
    private JFXTextField catagoryTxt;

    @FXML
    private JFXTextField ItemTxt;

    @FXML
    private JFXTextField DescriptionTxt;

    @FXML
    private JFXTextField partsTxt;

    @FXML
    private JFXTextField priceTxt;

private OrderBo orderBo=new OrderBoImpl();
private CustomerBo customerBo=new CustomerBoImpl();


    public void initialize(){
        calculateDate();
        setOrderId();
        setCustomerId();
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
                    ItemTxt.getText(),
                    Double.parseDouble(priceTxt.getText())

            ));

            try {
                Boolean isSaved = orderBo.saveOrder(new OrderDto(
                        lblOrderId.getText(),
                        ItemTxt.getText(),
                        catagoryTxt.getText(),
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
        catagoryTxt.clear();
        DescriptionTxt.clear();
        ItemTxt.clear();
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
