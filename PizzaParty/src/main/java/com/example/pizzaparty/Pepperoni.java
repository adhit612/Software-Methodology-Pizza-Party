package com.example.pizzaparty;

import java.util.ArrayList;
import java.util.Arrays;

public class Pepperoni extends Pizza {

    public Pepperoni() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.PEPPERONI));
        this.sauce = Sauce.TOMATO;
    }

    @Override
    public double getSizePrice(){
        if(this.size == Size.SMALL) {
            return 10.99;
        }
        else if(this.size == Size.MEDIUM) {
            return 12.99;
        }
        else{
            return 14.99;
        }
    }

    @Override
    public double price(){
        return getSizePrice() + extraCheeseAmount() + extraSauceAmount();
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
        return "[Deluxe]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
