package com.example.pizzaparty;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType){
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe();
            case "Supreme" -> new Supreme();
            case "Meatzza" -> new Meatzza();
            case "Seafood" -> new Seafood();
            case "Pepperoni" -> new Pepperoni();
            default -> new BuildYourOwn();
        };
    }
}
