package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.Controllers.MainMenuController;
import com.example.pizzaparty.MainApplication;
import com.example.pizzaparty.Pizza;
import com.example.pizzaparty.PizzaMaker;
import com.example.pizzaparty.Topping;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SpecialtiesController {
    private ObservableList<String> specialtiesList;
    @FXML
    private MainMenuController mainMenuController;

    @FXML
    private ComboBox <String> specialtyPizzasComboBox;

    @FXML
    private ListView toppingsListView;

    public void initialize(){
        specialtiesList = FXCollections.observableArrayList("Deluxe","Supreme","Meatzza","Seafood","Pepperoni");
        specialtyPizzasComboBox.setItems(specialtiesList);
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

    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    public void specialtiesComboBoxAction(ActionEvent actionEvent) {
        String selectedPizzaName = specialtyPizzasComboBox.getSelectionModel().getSelectedItem();
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza selectedPizza = pizzaMaker.createPizza(selectedPizzaName);
        ObservableList <Topping> toppingsObservableList = FXCollections.observableList(selectedPizza.getToppings());
        toppingsListView.setItems(toppingsObservableList);
    }
}
