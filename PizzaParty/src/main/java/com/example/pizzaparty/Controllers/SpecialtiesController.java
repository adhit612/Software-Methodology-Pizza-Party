package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.*;
import com.example.pizzaparty.Controllers.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SpecialtiesController {
    private ObservableList<String> specialtiesList;
    @FXML
    private MainMenuController mainMenuController;

    @FXML
    private ComboBox <String> specialtyPizzasComboBox;

    @FXML
    private ListView toppingsListView;

    @FXML
    private ToggleGroup SizesGroup;

    @FXML
    private ImageView specialtyPizzaimageView;

    @FXML
    private TextField pizzaPriceTextField;

    @FXML
    private TextField setSauceTextField;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private CheckBox extraSauceCheckBox;

    Pizza pizzaToBeAdded;

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

    public void specialtiesComboBoxAction(ActionEvent actionEvent) {
        String selectedPizzaName = specialtyPizzasComboBox.getSelectionModel().getSelectedItem();
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza selectedPizza = pizzaMaker.createPizza(selectedPizzaName);
        ObservableList <Topping> toppingsObservableList = FXCollections.observableList(selectedPizza.getToppings());
        toppingsListView.setItems(toppingsObservableList);
        setPizzaImageViewHelper(selectedPizzaName);
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(selectedPizzaName.equals("Deluxe")){
            pizzaToBeAdded = new Deluxe();
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Supreme")){
            pizzaToBeAdded = new Supreme();
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Meatzza")){
            pizzaToBeAdded = new Meatzza();
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Seafood")){
            pizzaToBeAdded = new Seafood();
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if(selectedPizzaName.equals("Pepperoni")){
            pizzaToBeAdded = new Pepperoni();
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        extraCheeseAndSauceHelper();
    }

    private void extraCheeseAndSauceHelper(){
        if(pizzaToBeAdded != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
        }
    }

    private void setPizzaGivenSizeUpperLayerHelper(RadioButton selectedSizeButton){
        if(pizzaToBeAdded.getSize() == null){
            if(selectedSizeButton != null){
                setPizzaPriceGivenSizeHelper(selectedSizeButton);
            }
        }
    }

    private void setPizzaPriceGivenSizeHelper(RadioButton selectedSizeButton){
        String selectedSizeStr = selectedSizeButton.getText();
        if(selectedSizeStr.equals("Small")){
            pizzaToBeAdded.setSize(Size.SMALL);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSizeStr.equals("Medium")){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
        else if(selectedSizeStr.equals("Large")){
            pizzaToBeAdded.setSize(Size.LARGE);
            if(extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if(extraSauceCheckBox.isSelected()){
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    private void setPizzaImageViewHelper(String selectedPizzaName){
        if(selectedPizzaName.equals("Deluxe")){
            try {
                InputStream stream = new FileInputStream("PizzaParty/src/main/resources/com/example/pizzaparty/Images/deluxePizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else if(selectedPizzaName.equals("Supreme")){
            try {
                InputStream stream = new FileInputStream("PizzaParty/src/main/resources/com/example/pizzaparty/Images/supremePizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else{
            setPizzaimageViewHelperTwo(selectedPizzaName);
        }
    }

    private void setPizzaimageViewHelperTwo(String selectedPizzaName){
        if(selectedPizzaName.equals("Meatzza")){
            try {
                InputStream stream = new FileInputStream("PizzaParty/src/main/resources/com/example/pizzaparty/Images/meatzzaPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else if(selectedPizzaName.equals("Seafood")){
            try {
                InputStream stream = new FileInputStream("PizzaParty/src/main/resources/com/example/pizzaparty/Images/seafoodPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        else{
            try {
                InputStream stream = new FileInputStream("PizzaParty/src/main/resources/com/example/pizzaparty/Images/pepperoniPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    public void setMainController (MainMenuController controller){
        mainMenuController = controller;
    }

    public void smallButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.SMALL);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    public void mediumButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.MEDIUM);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    public void largeButtonAction(ActionEvent actionEvent) {
        if(pizzaToBeAdded != null){
            pizzaToBeAdded.setSize(Size.LARGE);
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
        }
    }

    public void extraCheeseBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
            else if (!extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }

    public void extraSauceBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();
        if(pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
            else if (!extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }

    public void addToOrderButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton = (RadioButton) SizesGroup.getSelectedToggle();

        if(selectedSizeButton == null || specialtyPizzasComboBox.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields Message");
            alert.setHeaderText("Incomplete Fields!!");
            alert.setContentText("Please enter in all required fields to place an order.");
            alert.showAndWait();
        }
        else{
            if(pizzaToBeAdded != null){
                ArrayList <Pizza> updatedPizzaList = MainMenuController.getCurrentPizzaList();
                updatedPizzaList.add(pizzaToBeAdded);
                MainMenuController.setCurrentPizzaList(updatedPizzaList);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Pizza Added Message");
                alert.setHeaderText("Pizza has been added!");
                alert.setContentText("It will be worth it...");
                alert.showAndWait();
                //clear everything
//                specialtyPizzasComboBox.getSelectionModel().clearSelection();
//                selectedSizeButton.setSelected(false);
//                extraCheeseCheckBox.setSelected(false);
//                extraSauceCheckBox.setSelected(false);
//                setSauceTextField.setText("");
//                pizzaPriceTextField.setText("");
//                toppingsListView.getItems().removeAll();
//                specialtyPizzaimageView.setImage(null);
            }
        }
    }
}
