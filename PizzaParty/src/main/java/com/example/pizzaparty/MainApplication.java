package com.example.pizzaparty;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that manages the main application and running the program.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class MainApplication extends javafx.application.Application {

    private Button specialtyButton;

    /**
     * Method that handles starting the pizza application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.
                getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that handles main method, launching application.
     */
    public static void main(String[] args) {
        launch();
    }
}