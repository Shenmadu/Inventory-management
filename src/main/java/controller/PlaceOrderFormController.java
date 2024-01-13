package controller;

import bo.custom.CustomerBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Orders;
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
   public void placeButtonOnAction(ActionEvent event) {
        try {
            Boolean isSaved = orderBo.saveOrder(new Orders(
                    orderIdTxt.getText(),
                    NumberTxt.getText(),
                    ItemTxt.getText(),
                    catagoryTxt.getText(),
                    lblDate.getText(),
                    DescriptionTxt.getText(),
                    "pending"
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            customerBo.saveCustomer(new Customer(
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

}
