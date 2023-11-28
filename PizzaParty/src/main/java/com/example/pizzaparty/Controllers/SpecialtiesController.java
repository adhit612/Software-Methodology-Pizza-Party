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

/**
 * Controller class that manages actions for speciality pizzas.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class SpecialtiesController {
    private ObservableList<String> specialtiesList;
    @FXML
    private MainMenuController mainMenuController;

    @FXML
    private ComboBox<String> specialtyPizzasComboBox;

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

    private Pizza pizzaToBeAdded;

    private DataSingleton dataSingleton = DataSingleton.getInstance();

    /**
     * Method that initializes the layout for the current order screen.
     */
    public void initialize() {
        pizzaPriceTextField.setEditable(false);
        setSauceTextField.setEditable(false);
        specialtiesList = FXCollections.observableArrayList("Deluxe",
                "Supreme", "Meatzza", "Seafood", "Pepperoni");
        specialtyPizzasComboBox.setItems(specialtiesList);
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
     * Method that handles selecting a speciality pizza.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void specialtiesComboBoxAction(ActionEvent actionEvent) {
        String selectedPizzaName =
                specialtyPizzasComboBox.getSelectionModel().getSelectedItem();
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza selectedPizza = pizzaMaker.createPizza(selectedPizzaName);
        pizzaToBeAdded = pizzaMaker.createPizza(selectedPizzaName);
        ObservableList<Topping> toppingsObservableList =
                FXCollections.observableList(selectedPizza.getToppings());
        toppingsListView.setItems(toppingsObservableList);
        setPizzaImageViewHelper(selectedPizzaName);
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        if (selectedPizzaName.equals("Deluxe")) {
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if (selectedPizzaName.equals("Supreme")) {
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if (selectedPizzaName.equals("Meatzza")) {
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if (selectedPizzaName.equals("Seafood")) {
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        else if (selectedPizzaName.equals("Pepperoni")) {
            setSauceTextField.setText(pizzaToBeAdded.getSauceAsString());
            setPizzaGivenSizeUpperLayerHelper(selectedSizeButton);
        }
        extraCheeseAndSauceHelper();
    }

    /**
     * Method that handles adding extra sauce or cheese.
     */
    private void extraCheeseAndSauceHelper() {
        if (pizzaToBeAdded != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
        }
    }

    /**
     * Method that handles the size for pizza button.
     *
     * @param selectedSizeButton button click event.
     */
    private void setPizzaGivenSizeUpperLayerHelper(RadioButton selectedSizeButton) {
        if (pizzaToBeAdded.getSize() == null) {
            if (selectedSizeButton != null) {
                setPizzaPriceGivenSizeHelper(selectedSizeButton);
            }
        }
    }

    /**
     * Method that handles setting the pizza price.
     *
     * @param selectedSizeButton button click event.
     */
    private void setPizzaPriceGivenSizeHelper(RadioButton selectedSizeButton) {
        String selectedSizeStr = selectedSizeButton.getText();
        if (selectedSizeStr.equals("Small")) {
            pizzaToBeAdded.setSize(Size.SMALL);
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
        else if (selectedSizeStr.equals("Medium")) {
            pizzaToBeAdded.setSize(Size.MEDIUM);
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
        else if (selectedSizeStr.equals("Large")) {
            pizzaToBeAdded.setSize(Size.LARGE);
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
            }
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
            }
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
    }

    /**
     * Method that handles images of pizza views.
     *
     * @param selectedPizzaName name of pizza selected.
     */
    private void setPizzaImageViewHelper(String selectedPizzaName) {
        if (selectedPizzaName.equals("Deluxe")) {
            try {
                InputStream stream = new FileInputStream(
                        "PizzaParty/src" +
                        "/main/resources/com/example/pizzaparty/Images" +
                        "/deluxePizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (selectedPizzaName.equals("Supreme")) {
            try {
                InputStream stream = new FileInputStream(
                        "PizzaParty/src" +
                        "/main/resources/com/example/pizzaparty/Images" +
                        "/supremePizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            setPizzaimageViewHelperTwo(selectedPizzaName);
        }
    }

    /**
     * Method that handles setting the image of pizza view.
     *
     * @param selectedPizzaName the selected pizza.
     */
    private void setPizzaimageViewHelperTwo(String selectedPizzaName) {
        if (selectedPizzaName.equals("Meatzza")) {
            try {
                InputStream stream = new FileInputStream(
                        "PizzaParty/src" +
                        "/main/resources/com/example/pizzaparty/Images" +
                        "/meatzzaPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (selectedPizzaName.equals("Seafood")) {
            try {
                InputStream stream = new FileInputStream(
                        "PizzaParty/src" +
                        "/main/resources/com/example/pizzaparty/Images" +
                        "/seafoodPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                InputStream stream = new FileInputStream(
                        "PizzaParty/src" +
                        "/main/resources/com/example/pizzaparty/Images" +
                        "/pepperoniPizzaImage.jpg");
                Image image = new Image(stream);
                specialtyPizzaimageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that handles setting the main menu controller.
     *
     * @param controller main menu controller.
     */
    public void setMainController(MainMenuController controller) {
        mainMenuController = controller;
    }

    /**
     * Method that handles size of pizza small.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void smallButtonAction(ActionEvent actionEvent) {
        if (pizzaToBeAdded != null) {
            pizzaToBeAdded.setSize(Size.SMALL);
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
    }

    /**
     * Method that handles size of pizza medium.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void mediumButtonAction(ActionEvent actionEvent) {
        if (pizzaToBeAdded != null) {
            pizzaToBeAdded.setSize(Size.MEDIUM);
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
    }

    /**
     * Method that handles size of pizza large.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void largeButtonAction(ActionEvent actionEvent) {
        if (pizzaToBeAdded != null) {
            pizzaToBeAdded.setSize(Size.LARGE);
            pizzaPriceTextField.setText(String.valueOf(
                    pizzaToBeAdded.price()));
        }
    }

    /**
     * Method that handles extra cheese box.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void extraCheeseBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        if (pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(
                        pizzaToBeAdded.price()));
            }
            else if (!extraCheeseCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraCheese();
                pizzaPriceTextField.setText(String.valueOf(
                        pizzaToBeAdded.price()));
            }
        }
    }

    /**
     * Method that handles extra sauce box.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void extraSauceBoxAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();
        if (pizzaToBeAdded != null && selectedSizeButton != null) {
            if (extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.addExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(
                        pizzaToBeAdded.price()));
            }
            else if (!extraSauceCheckBox.isSelected()) {
                pizzaToBeAdded.removeExtraSauce();
                pizzaPriceTextField.setText(String.valueOf(
                        pizzaToBeAdded.price()));
            }
        }
    }

    /**
     * Method that handles adding pizza to order.
     *
     * @param actionEvent button click event.
     */
    @FXML
    public void addToOrderButtonAction(ActionEvent actionEvent) {
        RadioButton selectedSizeButton =
                (RadioButton) SizesGroup.getSelectedToggle();

        if (selectedSizeButton == null || specialtyPizzasComboBox.
                getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields Message");
            alert.setHeaderText("Incomplete Fields!!");
            alert.setContentText("Please enter in all required fields to " +
                    "place an order.");
            alert.showAndWait();
        }
        else {
            if (pizzaToBeAdded != null) {
                Order order = dataSingleton.getOrder();
                if (order == null) {
                    ArrayList<Pizza> pizzaList = new ArrayList<>();
                    pizzaList.add(pizzaToBeAdded);
                    Order newOrder = new Order(pizzaList);
                    dataSingleton.setOrder(newOrder);
                }
                else {
                    order.add(pizzaToBeAdded);
                    dataSingleton.setOrder(order);
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Pizza Added Message");
                alert.setHeaderText("Pizza has been added!");
                alert.setContentText("It will be worth it...");
                alert.showAndWait();
            }
        }
    }
}
