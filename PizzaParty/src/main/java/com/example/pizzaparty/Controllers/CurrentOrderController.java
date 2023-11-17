package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CurrentOrderController {
    private MainMenuController mainMenuController;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private TextField subtotalTextField;

    @FXML
    private TextField salesTaxTextField;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private ListView orderContentsListView;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    public void initialize(){
        orderNumberTextField.setEditable(false);
        subtotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        Order currOrder = dataSingleton.getOrder();

        if(currOrder != null){
            if(storeOrders == null){
                ArrayList <Order> orderList = new ArrayList<>();
                StoreOrders newStoreOrder = new StoreOrders(orderList);
                newStoreOrder.add(currOrder);
                dataSingleton.setStoreOrders(newStoreOrder);
                orderNumberTextField.setText(String.valueOf(currOrder.getOrderNumber()));
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(currOrder.getPizzaList());
                orderContentsListView.setItems(orderPizzaObservableList);
            }
            else{
                storeOrders.add(currOrder);
                dataSingleton.setStoreOrders(storeOrders);
                orderNumberTextField.setText(String.valueOf(currOrder.getOrderNumber()));
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(currOrder.getPizzaList());
                orderContentsListView.setItems(orderPizzaObservableList);
            }
        }
    }

    public void backToMainAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Main Menu");
            mainStage.show();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    public void setMainController (MainMenuController controller) {
        mainMenuController = controller;
    }

    public void placeOrderButtonAction(ActionEvent actionEvent) {
        Order currOrder = dataSingleton.getOrder();
        currOrder = null;
        dataSingleton.setOrder(currOrder);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Placed Message");
        alert.setHeaderText("Order has been placed!");
        alert.setContentText("Enjoy the food...");
        alert.showAndWait();
    }
}
