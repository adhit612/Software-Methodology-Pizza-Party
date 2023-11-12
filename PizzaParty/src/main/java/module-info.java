module com.example.pizzaparty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pizzaparty to javafx.fxml;
    exports com.example.pizzaparty;
    exports com.example.pizzaparty.Controllers;
    opens com.example.pizzaparty.Controllers to javafx.fxml;
}