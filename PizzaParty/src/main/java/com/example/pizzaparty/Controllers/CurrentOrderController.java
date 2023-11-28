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

/**
 * Controller class that manages actions for current orders.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
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

    private DataSingleton dataSingleton = DataSingleton.getInstance();

    /**
     * Method that initializes the layout for the current order screen.
     */
    public void initialize() {
        orderNumberTextField.setEditable(false);
        subtotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        orderTotalTextField.setEditable(false);
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        Order currOrder = dataSingleton.getOrder();

        if (currOrder != null) {
            if (storeOrders == null) {
                ArrayList<Order> orderList = new ArrayList<>();
                StoreOrders newStoreOrder = new StoreOrders(orderList);
                newStoreOrder.add(currOrder);
                dataSingleton.setStoreOrders(newStoreOrder);
                orderNumberTextField.setText(String.valueOf(currOrder.
                        getOrderNumber()));
                ObservableList<Pizza> orderPizzaObservableList =
                        FXCollections.observableList(currOrder.
                                getPizzaList());
                orderContentsListView.setItems(orderPizzaObservableList);
                subtotalTextField.setText(String.valueOf(currOrder.
                        getTotalPriceWithoutTax()));
                salesTaxTextField.setText(String.valueOf(currOrder.
                        getSalesTaxOfTotal()));
                orderTotalTextField.setText(String.valueOf(currOrder.
                        getTotalPriceWithSalesTax()));
            }
            else {
                initalizeHelper();
            }
        }
    }

    /**
     * Helper method that initializes the layout for the current order screen.
     */
    private void initalizeHelper() {
        StoreOrders storeOrders = dataSingleton.getStoreOrders();
        Order currOrder = dataSingleton.getOrder();
        storeOrders.add(currOrder);
        dataSingleton.setOrderAdded(true);
        dataSingleton.setStoreOrders(storeOrders);
        orderNumberTextField.setText(String.valueOf(currOrder.
                getOrderNumber()));
        ObservableList<Pizza> orderPizzaObservableList =
                FXCollections.observableList(currOrder.getPizzaList());
        orderContentsListView.setItems(orderPizzaObservableList);
        subtotalTextField.setText(String.valueOf(currOrder.
                getTotalPriceWithoutTax()));
        salesTaxTextField.setText(String.valueOf(currOrder.
                getSalesTaxOfTotal()));
        orderTotalTextField.setText(String.valueOf(currOrder.
                getTotalPriceWithSalesTax()));
    }

    /**
     * Method that handles going back to the main menu.
     *
     * @param actionEvent button click event.
     */
    public void backToMainAction(ActionEvent actionEvent) {
        Order currOrder = dataSingleton.getOrder();
        if (currOrder != null) {
            StoreOrders storeOrders = dataSingleton.getStoreOrders();
            storeOrders.remove(currOrder);
        }

        Stage mainStage =
                (Stage) ((Node) actionEvent.getSource()).
                        getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader =
                    new FXMLLoader(MainApplication.class.
                            getResource("main" +
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
     * Method that handles placing an order button.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void placeOrderButtonAction(ActionEvent actionEvent) {
        Order currOrder = dataSingleton.getOrder();

        if (currOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order Already Placed Message");
            alert.setHeaderText("Order has already been placed!");
            alert.setContentText("Fun is on the way...");
            alert.showAndWait();
            return;
        }

        ArrayList<Pizza> orderPizzaList = currOrder.getPizzaList();

        if (orderPizzaList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Order Message");
            alert.setHeaderText("Your order is empty!");
            alert.setContentText("Add a pizza to enjoy...");
            alert.showAndWait();
            return;
        }
        currOrder = null;
        dataSingleton.setOrder(currOrder);
        dataSingleton.setOrderAdded(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order Placed Message");
        alert.setHeaderText("Order has been placed!");
        alert.setContentText("Enjoy the food...");
        alert.showAndWait();
    }

    /**
     * Method that handles removing the selected pizza.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void removeSelectedPizzaAction(ActionEvent actionEvent) {
        Pizza selectedPizza =
                (Pizza) orderContentsListView.getSelectionModel().
                        getSelectedItem();
        if (selectedPizza == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please Select Item Message");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("If you really wanna remove you gotta " +
                    "select...");
            alert.showAndWait();
        }
        else {
            Order currOrder = dataSingleton.getOrder();
            if (currOrder == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Order Already Placed Message");
                alert.setHeaderText("Order has already been placed!");
                alert.setContentText("Fun is on the way...");
                alert.showAndWait();
                return;
            }
            ArrayList<Pizza> orderPizzaList = currOrder.getPizzaList();
            int indexToRemove =
                    orderContentsListView.getSelectionModel().
                            getSelectedIndex();
            orderPizzaList.remove(indexToRemove);
            currOrder.setPizzasList(orderPizzaList);
            dataSingleton.setOrder(currOrder);
            ObservableList<Pizza> orderPizzaObservableList =
                    FXCollections.observableList(currOrder.getPizzaList());
            orderContentsListView.setItems(orderPizzaObservableList);
            subtotalTextField.setText(String.valueOf(currOrder.
                    getTotalPriceWithoutTax()));
            salesTaxTextField.setText(String.valueOf(currOrder.
                    getSalesTaxOfTotal()));
            orderTotalTextField.setText(String.valueOf(currOrder.
                    getTotalPriceWithSalesTax()));
        }
    }
}
