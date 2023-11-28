package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.util.ArrayList;

/**
 * Controller class that manages actions for building your own pizza.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class BuildOwnController {
    private MainMenuController mainMenuController;

    @FXML
    private ToggleGroup SizesGroup;

    @FXML
    private ToggleGroup SaucesGroup;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private CheckBox extraSauceCheckBox;

    @FXML
    private ListView toppingsListView;

    @FXML
    private ListView selectedToppingsListView;

    @FXML
    private TextField pizzaPriceTextField;

    private int toppingCount;

    private Pizza pizzaToBeAdded;

    private DataSingleton dataSingleton = DataSingleton.getInstance();

    /**
     * Method that initializes the layout for creating the pizza.
     */
    public void initialize() {
        pizzaPriceTextField.setEditable(false);
        toppingCount = 0;
        PizzaMaker pizzaMaker = new PizzaMaker();
        pizzaToBeAdded = pizzaMaker.createPizza("Build Your Own");
        ObservableList<Topping> toppingsObservableList =
                FXCollections.observableArrayList(Topping.BEEF, Topping.HAM
                        , Topping.BLACKOLIVE, Topping.CHICKEN,
                        Topping.CRABMEATS, Topping.GREENPEPPER,
                        Topping.HOTSAUCE,
                        Topping.MUSHROOM, Topping.ONION,
                        Topping.PEPPERONI, Topping.SAUSAGE, Topping.SHRIMP,
                        Topping.SQUID);
        toppingsListView.setItems(toppingsObservableList);
    }

    /**
     * Method that handles going back to the main menu.
     *
     * @param actionEvent button click event.
     */
    @FXML
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
     * Method that handles action for adding a topping.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void addToppingButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton selectedSauceButton =
                (RadioButton) SaucesGroup.getSelectedToggle();
        if (selectedSizeButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Size");
            alert.setHeaderText("Please select Size");
            alert.setContentText("Size before toppings...");
            alert.showAndWait();
        }
        else if (selectedSauceButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Sauce");
            alert.setHeaderText("Please select Sauce");
            alert.setContentText("Sauce before toppings...");
            alert.showAndWait();
        }
        else {
            Topping selectedTopping =
                    (Topping) toppingsListView.getSelectionModel().
                            getSelectedItem();
            if (selectedTopping == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Select Topping");
                alert.setHeaderText("Please select Topping");
                alert.setContentText("Pick a topping to add...");
                alert.showAndWait();
            }
            else {
                addButtonHelper(selectedTopping);
            }
        }
    }

    /**
     * Method that handles adding a topping to the list.
     *
     * @param selectedTopping the current topping selected.
     */
    public void addButtonHelper(Topping selectedTopping) {
        ObservableList<Topping> toppingsList =
                selectedToppingsListView.getItems();
        if (toppingsList == null) {
            ArrayList<Topping> newToppingList = new ArrayList<>();
            newToppingList.add(selectedTopping);
            ObservableList<Topping> toppingArrayListToBeSet =
                    FXCollections.observableList(newToppingList);
            selectedToppingsListView.setItems(toppingArrayListToBeSet);
        }
        else {
            if (toppingsList.contains(selectedTopping)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Topping Already Added");
                alert.setHeaderText("Topping has already been added");
                alert.setContentText("Pick something else...");
                alert.showAndWait();
            }
            else {
                if (toppingCount >= 7) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Topping Limit Reached");
                    alert.setHeaderText("Topping Limit has been Reached!");
                    alert.setContentText("Woah there...");
                    alert.showAndWait();
                }
                else {
                    toppingsList.add(selectedTopping);
                    if (toppingCount >= 3) {
                        pizzaToBeAdded.incrementToppingsAmount();
                    }
                    selectedToppingsListView.setItems(toppingsList);
                    pizzaPriceTextField.setText
                            (String.valueOf(pizzaToBeAdded.price()));
                    toppingCount++;
                }
            }
        }
    }

    /**
     * Method that handles removing a topping from the list.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void removeToppingButtonAction(ActionEvent actionEvent) {
        Topping selectedTopping =
                (Topping) selectedToppingsListView.
                        getSelectionModel().getSelectedItem();
        if (selectedTopping == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Topping to Remove");
            alert.setHeaderText("Please Select a Topping to Remove");
            alert.setContentText("Pick something to remove...");
            alert.showAndWait();
        }
        else {
            ObservableList<Topping> selectedToppingsList =
                    selectedToppingsListView.getItems();
            selectedToppingsList.remove(selectedTopping);
            toppingCount--;
            if (toppingCount >= 3) {
                pizzaToBeAdded.decrementToppingsAmount();
            }
            pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            selectedToppingsListView.setItems(selectedToppingsList);
        }
    }

    /**
     * Method that handles the small size.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void smallRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.SMALL);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    /**
     * Method that handles the medium size.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void mediumRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.MEDIUM);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    /**
     * Method that handles the large size.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void largeRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSize(Size.LARGE);
        pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
    }

    /**
     * Method that handles the tomato sauce.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void tomatoSauceRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSauce(Sauce.TOMATO);
    }

    /**
     * Method that handles the alfredo sauce.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void alfredoSauceRadioButtonAction(ActionEvent actionEvent) {
        pizzaToBeAdded.setSauce(Sauce.ALFREDO);
    }

    /**
     * Method that handles putting the pizza to the order.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void addToOrderButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton selectedSauceButton =
                (RadioButton) SaucesGroup.getSelectedToggle();
        if (selectedSizeButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Size");
            alert.setHeaderText("Please select Size");
            alert.setContentText("Size before toppings...");
            alert.showAndWait();
        }
        else if (selectedSauceButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Sauce");
            alert.setHeaderText("Please select Sauce");
            alert.setContentText("Sauce before toppings...");
            alert.showAndWait();
        }
        else {
            addToOrderButtonHelper();
        }
    }

    /**
     * Method that handles adding the pizza to the order.
     */
    public void addToOrderButtonHelper() {
        ObservableList<Topping> selectedToppingsList =
                selectedToppingsListView.getItems();
        if (selectedToppingsList == null || selectedToppingsList.size() < 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Toppings");
            alert.setHeaderText("Please Select Toppings");
            alert.setContentText("At least 3 toppings needed...");
            alert.showAndWait();
        }
        else {
            addToOrderButtonHelperTwo(selectedToppingsList);
        }
    }

    /**
     * Method helper that handles adding the pizza to the order.
     *
     * @param selectedToppingsList the toppingList to be added to pizza
     */
    private void addToOrderButtonHelperTwo(ObservableList<Topping> selectedToppingsList) {
        if (pizzaToBeAdded != null) {
            Order order = dataSingleton.getOrder();
            if (order == null) {
                ArrayList<Pizza> pizzaList = new ArrayList<>();
                ArrayList<Topping> toppingsToBeSet = new ArrayList<>();
                if (selectedToppingsList instanceof ArrayList<?>) {
                    toppingsToBeSet =
                            (ArrayList<Topping>) selectedToppingsList;
                }
                else {
                    toppingsToBeSet =
                            new ArrayList<>(selectedToppingsList);
                }
                pizzaToBeAdded.setToppings(toppingsToBeSet);
                pizzaList.add(pizzaToBeAdded);
                Order newOrder = new Order(pizzaList);
                dataSingleton.setOrder(newOrder);
            }
            else {
                ArrayList<Topping> toppingsToBeSet = new ArrayList<>();
                if (selectedToppingsList instanceof ArrayList<?>) {
                    toppingsToBeSet =
                            (ArrayList<Topping>) selectedToppingsList;
                }
                else {
                    toppingsToBeSet =
                            new ArrayList<>(selectedToppingsList);
                }
                pizzaToBeAdded.setToppings(toppingsToBeSet);
                order.add(pizzaToBeAdded);
                dataSingleton.setOrder(order);
            }
            createAddedAlert();
        }
    }

    /**
     * Method that handles alerts for adding pizza.
     */
    public void createAddedAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pizza Added Message");
        alert.setHeaderText("Pizza has been added!");
        alert.setContentText("It will be worth it...");
        alert.showAndWait();
    }

    /**
     * Method that handles extra sauce checkbox.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void extraSauceCheckBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton selectedSauceButton =
                (RadioButton) SaucesGroup.getSelectedToggle();
        if (selectedSizeButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Size");
            alert.setHeaderText("Please select Size");
            alert.setContentText("Size before toppings...");
            alert.showAndWait();
        }
        else if (selectedSauceButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Sauce");
            alert.setHeaderText("Please select Sauce");
            alert.setContentText("Sauce before toppings...");
            alert.showAndWait();
        }
        else {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
            else {
                pizzaToBeAdded.removeExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }

    /**
     * Method that handles extra cheese checkbox.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void extraCheeseCheckBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        RadioButton selectedSauceButton =
                (RadioButton) SaucesGroup.getSelectedToggle();
        if (selectedSizeButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Size");
            alert.setHeaderText("Please select Size");
            alert.setContentText("Size before toppings...");
            alert.showAndWait();
        }
        else if (selectedSauceButton == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Sauce");
            alert.setHeaderText("Please select Sauce");
            alert.setContentText("Sauce before toppings...");
            alert.showAndWait();
        }
        else {
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
                pizzaPriceTextField.setText
                        (String.valueOf(pizzaToBeAdded.price()));
            }
            else {
                pizzaToBeAdded.removeExtraCheese();
                pizzaPriceTextField.setText
                        (String.valueOf(pizzaToBeAdded.price()));
            }
        }
    }
}
