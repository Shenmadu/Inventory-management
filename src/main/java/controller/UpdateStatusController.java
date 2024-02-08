package controller;

import bo.custom.OrderBo;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UpdateStatusController {

    public JFXTextField idTxt;
    public JFXTextField codeTxt;
    public JFXTextField statusTxt;

    public JFXButton backButton;
    public JFXTextField dateTxt;
    public JFXButton updateBtn;
    public JFXButton addPartsBtn;
    public JFXButton billBtn;
//    OrderDao orderDao=new OrderDaoImpl();
    OrderBo orderBo=new OrderBoImpl();

    public void initialize(){
        addPartsBtn.setVisible(false);
        billBtn.setVisible(false);
    }
    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        try {
            OrderDto orderDto =orderBo.searchOrder(idTxt.getText());

            if (orderDto!=null){
                codeTxt.setText(orderDto.getItem());
                statusTxt.setText(orderDto.getStatus());
                dateTxt.setText(orderDto.getDate());

            }else {
                new Alert(Alert.AlertType.INFORMATION, "Invalid Order Id!").show();
                idTxt.clear();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (statusTxt.getText().equals("Processing")){
            addPartsBtn.setVisible(true);
        }  else  if (statusTxt.getText().equals("Closed")){
            billBtn.setVisible(true);
        }

}

    public void addPartsBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) addPartsBtn.getScene().getWindow();

        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/AddParts.fxml"));
            Scene scene = new Scene(loader.load());

            AddPartsContoller partsContoller=loader.getController();
            partsContoller.setOrderId(idTxt.getText());
            partsContoller.setItemcode(codeTxt.getText());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void billBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) billBtn.getScene().getWindow();
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/OrderBillForm.fxml"));
            Scene scene = new Scene(loader.load());

            OrderBillContoller billContoller=loader.getController();
            billContoller.setOrderId(idTxt.getText());
            billContoller.setItemcode(codeTxt.getText());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBtnOnAction(ActionEvent actionEvent) {
        String currentStatus = statusTxt.getText();
        String newStatus = "";

        if (currentStatus.equals("pending")) {
            newStatus = "Processing";
            addPartsBtn.setVisible(true);
        } else if (currentStatus.equals("Processing")) {
            newStatus = "Completed";
        } else  if (currentStatus.equals("Completed"))  {
            newStatus = "Closed";
            billBtn.setVisible(true);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Set status to " + newStatus);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            orderBo.updateStatus(idTxt.getText(),newStatus);
            statusTxt.setText(newStatus);

        }
    }

}
