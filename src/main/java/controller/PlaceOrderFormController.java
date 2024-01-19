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
//        List<OrderDetailsDto> list = new ArrayList<>();
//        list.add(new OrderDetailsDto(
//                orderIdTxt.getText(),
//                ItemTxt.getText(),
//                Double.parseDouble(priceTxt.getText()),
//                "pending"
//
//
//
//        ));


        try {
            Boolean isSaved = orderBo.saveOrder(new OrderDto(

                    orderIdTxt.getText(),
                    NumberTxt.getText(),
                    ItemTxt.getText(),
                    catagoryTxt.getText(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
                    DescriptionTxt.getText(),
                    "pending"

            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed succesfull!").show();
                clearFields();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            customerBo.saveCustomer(new CustomerDto(
                    NumberTxt.getText(),
                    nameTxt.getText(),
                    emailTxt.getText()


            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void clearFields() {
        orderIdTxt.clear();
        nameTxt.clear();
        NumberTxt.clear();
        emailTxt.clear();
        catagoryTxt.clear();
        DescriptionTxt.clear();
        ItemTxt.clear();
        partsTxt.clear();
        priceTxt.clear();
    }

}
