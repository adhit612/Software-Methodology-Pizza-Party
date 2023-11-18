package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.Controllers.MainMenuController;
import com.example.pizzaparty.MainApplication;
import com.example.pizzaparty.Order;
import com.example.pizzaparty.Pizza;
import com.example.pizzaparty.StoreOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreOrdersController {
    private MainMenuController mainMenuController;

    private ObservableList<Integer> orderNumberList;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private ComboBox orderNumbersComboBox;

    @FXML
    private ListView orderInformationListView;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    public void initialize(){
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList <Integer> orderNumbersToBeAdded = new ArrayList<>();
        if(storeOrders != null) {
            ArrayList<Order> orderList = storeOrders.getOrders();
            for(int i = 0; i < orderList.size(); i ++){
                orderNumbersToBeAdded.add(orderList.get(i).getOrderNumber());
            }
            orderNumberList = FXCollections.observableArrayList(orderNumbersToBeAdded);
            orderNumbersComboBox.setItems(orderNumberList);
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

    public void orderNumbersComboBoxAction(ActionEvent actionEvent) {
        Object selectedOrder = orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if(selectedOrder == null){
            orderTotalTextField.clear();
            return;
        }
        int selectedOrderNumber = (int)orderNumbersComboBox.getSelectionModel().getSelectedItem();
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList <Order> orders = storeOrders.getOrders();
        for(int i = 0; i < orders.size(); i ++){
            if(orders.get(i).getOrderNumber() == selectedOrderNumber){
                ObservableList<Pizza> orderPizzaObservableList = FXCollections.observableList(orders.get(i).getPizzaList());
                orderInformationListView.setItems(orderPizzaObservableList);
                orderTotalTextField.setText(String.valueOf(orders.get(i).getTotalPriceWithSalesTax()));
            }
        }
    }

    public void cancelOrderButtonAction(ActionEvent actionEvent) {
        Object selectedOrder = orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if(selectedOrder == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Select Order Number Message");
            alert.setHeaderText("Please select an order number to remove");
            alert.setContentText("To remove you must select...");
            alert.showAndWait();
        }
        else{
            int selectedOrderNumber = (int)orderNumbersComboBox.getSelectionModel().getSelectedItem();
            StoreOrders storeOrders = dataSingleton.getStoreOrders();
            ArrayList <Order> orders = storeOrders.getOrders();
            int indexToRemove = -1;
            for(int i = 0; i < orders.size(); i ++) {
                if (orders.get(i).getOrderNumber() == selectedOrderNumber) {
                    indexToRemove = i;
                    break;
                }
            }
            orders.remove(indexToRemove);
            storeOrders.setOrders(orders);
            dataSingleton.setStoreOrders(storeOrders);
            orderInformationListView.getItems().clear();
            ObservableList <Integer> orderNumbers = orderNumbersComboBox.getItems();
            for(int i = 0; i < orderNumbers.size(); i ++){
                if(orderNumbers.get(i) == selectedOrderNumber){
                    indexToRemove = i;
                }
            }
            orderNumbers.remove(indexToRemove);
            orderNumbersComboBox.setItems(orderNumbers);
        }
    }
}
