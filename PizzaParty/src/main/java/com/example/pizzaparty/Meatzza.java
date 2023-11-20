package com.example.pizzaparty;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Meatzza extends Pizza {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Meatzza() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,Topping.PEPPERONI,Topping.BEEF,Topping.HAM));
        this.sauce = Sauce.TOMATO;
    }

    @Override
    public double getSizePrice(){
        if(this.size == Size.SMALL) {
            return 16.99;
        }
        else if(this.size == Size.MEDIUM) {
            return 18.99;
        }
        else{
            return 20.99;
        }
    }

    @Override
    public double price(){
        return Double.parseDouble(decimalFormat.format(getSizePrice() + extraCheeseAmount() + extraSauceAmount()));
    }

    @Override
    public String getToppingsAsString(){
        String toppingsAsString = "";
        for(int i = 0; i < this.toppings.size(); i ++){
            if(i == this.toppings.size()-1) {
                toppingsAsString += this.toppings.get(i).toString();
            }
            else {
                toppingsAsString += this.toppings.get(i).toString() + ", ";
            }
        }
        return toppingsAsString;
    }

    @Override
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    @Override
    public String toString(){
        return "[Meatzza]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
