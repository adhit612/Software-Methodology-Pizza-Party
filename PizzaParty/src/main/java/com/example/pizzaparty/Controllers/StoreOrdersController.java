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

/**
 * Controller class that manages actions store orders.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class StoreOrdersController {
    private MainMenuController mainMenuController;

    private ObservableList<Integer> orderNumberList;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private ComboBox orderNumbersComboBox;

    @FXML
    private ListView orderInformationListView;

    private DataSingleton dataSingleton = DataSingleton.getInstance();

    /**
     * Method that initializes the layout for the current order screen.
     */
    public void initialize() {
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList<Integer> orderNumbersToBeAdded = new ArrayList<>();
        if (storeOrders != null) {
            ArrayList<Order> orderList = storeOrders.getOrders();
            for (int i = 0; i < orderList.size(); i++) {
                orderNumbersToBeAdded.add(orderList.get(i).getOrderNumber());
            }
            orderNumberList =
                    FXCollections.observableArrayList(orderNumbersToBeAdded);
            orderNumbersComboBox.setItems(orderNumberList);
        }
    }

    /**
     * Method that handles going back to the main menu.
     *
     * @param actionEvent button click event.
     */
    public void backToMainAction(ActionEvent actionEvent) {
        Stage mainStage =
                (Stage) ((Node) actionEvent.getSource()).getScene().
                        getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader =
                    new FXMLLoader(MainApplication.class.getResource(
                            "main" +
                            "-menu.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Main Menu");
            mainStage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Method that sets the controller back to main.
     *
     * @param controller controller of main menu.
     */
    public void setMainController(MainMenuController controller) {
        mainMenuController = controller;
    }

    /**
     * Method that handles the numbers of each order.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void orderNumbersComboBoxAction(ActionEvent actionEvent) {
        Object selectedOrder =
                orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            orderTotalTextField.clear();
            return;
        }
        int selectedOrderNumber =
                (int) orderNumbersComboBox.getSelectionModel().
                        getSelectedItem();
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        ArrayList<Order> orders = storeOrders.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == selectedOrderNumber) {
                ObservableList<Pizza> orderPizzaObservableList =
                        FXCollections.observableList(orders.get(i).
                                getPizzaList());
                orderInformationListView.setItems(orderPizzaObservableList);
                orderTotalTextField.setText(String.valueOf(orders.get(i).
                        getTotalPriceWithSalesTax()));
            }
        }
    }

    /**
     * Method that handles canceling an order.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void cancelOrderButtonAction(ActionEvent actionEvent) {
        Object selectedOrder =
                orderNumbersComboBox.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Select Order Number Message");
            alert.setHeaderText("Please select an order number to remove");
            alert.setContentText("To remove you must select...");
            alert.showAndWait();
        }
        else {
            int selectedOrderNumber =
                    (int) orderNumbersComboBox.getSelectionModel().
                            getSelectedItem();
            StoreOrders storeOrders = dataSingleton.getStoreOrders();
            ArrayList<Order> orders = storeOrders.getOrders();
            int indexToRemove = -1;
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getOrderNumber() == selectedOrderNumber) {
                    indexToRemove = i;
                    break;
                }
            }
            orders.remove(indexToRemove);
            storeOrders.setOrders(orders);
            dataSingleton.setStoreOrders(storeOrders);
            orderInformationListView.getItems().clear();
            ObservableList<Integer> orderNumbers =
                    orderNumbersComboBox.getItems();
            for (int i = 0; i < orderNumbers.size(); i++) {
                if (orderNumbers.get(i) == selectedOrderNumber) {
                    indexToRemove = i;
                }
            }
            orderNumbers.remove(indexToRemove);
            orderNumbersComboBox.setItems(orderNumbers);
        }
    }

    /**
     * Method that handles exporting the store orders to a file.
     *
     * @param actionEvent button click event.
     */
    public void exportStoreOrdersAction(ActionEvent actionEvent) {
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        if (storeOrders == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Create Order Message");
            alert.setHeaderText("Please create an order to export");
            alert.setContentText("To print you must create...");
            alert.showAndWait();
        }
        else {
            ArrayList<Order> ordersList = storeOrders.getOrders();
            if (ordersList == null || ordersList.size() < 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Create Order Message");
                alert.setHeaderText("Please create an order to export");
                alert.setContentText("To print you must create...");
                alert.showAndWait();
            }
            else {
                storeOrders.export();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Order Exported Message");
                alert.setHeaderText("Order Has Been Exported");
                alert.setContentText("Do with it what you want...");
                alert.showAndWait();
            }
        }
    }
}
