package com.example.pizzaparty;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwn extends Pizza {

    public BuildYourOwn(){
        this.extraSauce = false;
        this.extraCheese = false;
    }

    @Override
    public double getSizePrice(){
        if(this.size == Size.SMALL) {
            return 8.99;
        }
        else if(this.size == Size.MEDIUM) {
            return 10.99;
        }
        else{
            return 12.99;
        }
    }

    @Override
    public double price(){
        return getSizePrice() + extraCheeseAmount() + extraSauceAmount();
    }

    @Override
    public String getToppingsAsString(){
        String toppingsAsString = "";

        if(this.toppings == null) {
            return "";
        }

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
    public String toString(){
        if(this.extraCheese == false || this.extraSauce == false){
            return "[Deluxe]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString().replace(",","") + extraSauceString().replace(",","") + ": " + "$" + price();
        }
        return "[Deluxe]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
