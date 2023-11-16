package com.example.pizzaparty;

import java.util.ArrayList;
import java.util.Arrays;

public class Seafood extends Pizza {

    public Seafood() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SHRIMP,Topping.SQUID,Topping.CRABMEATS));
        this.sauce = Sauce.ALFREDO;
    }

    @Override
    public double getSizePrice(){
        if(this.size == Size.SMALL) {
            return 17.99;
        }
        else if(this.size == Size.MEDIUM) {
            return 19.99;
        }
        else{
            return 21.99;
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
