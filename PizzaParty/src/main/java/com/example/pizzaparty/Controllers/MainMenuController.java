package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.MainApplication;
import com.example.pizzaparty.Order;
import com.example.pizzaparty.Pizza;
import com.example.pizzaparty.StoreOrders;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainMenuController {
    static ArrayList<Pizza> currentPizzaList;

    static Order currentOrder = new Order(currentPizzaList);

    static StoreOrders storeOrders = new StoreOrders();

    public void initialize(){
    }

    public static Order getCurrentOrder(){
        return currentOrder;
    }

    public static void setCurrentOrder(Order newOrder){
        currentOrder = newOrder;
    }

    @FXML
    public void specialtyButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("specialties.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Specialties");
            mainStage.show();
            SpecialtiesController specialtiesController = loader.getController();
            specialtiesController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    public void buildYourOwnButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("build-own.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Build Your Own");
            mainStage.show();
            BuildOwnController byoController = loader.getController();
            byoController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    public void currentOrderButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("current-order.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Current Order");
            mainStage.show();
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    public void storeOrdersButtonAction(ActionEvent actionEvent) {
        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("store-orders.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
            mainStage.setTitle("Store Orders");
            mainStage.show();
            StoreOrdersController storeOrdersController = loader.getController();
            storeOrdersController.setMainController(this);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
}