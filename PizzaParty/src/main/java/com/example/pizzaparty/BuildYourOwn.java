package com.example.pizzaparty;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwn extends Pizza {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public BuildYourOwn(){
        this.toppingsIncrement = 0.0;
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
        return Double.parseDouble(decimalFormat.format(getSizePrice() + extraCheeseAmount() + extraSauceAmount() + getToppingsIncrement()));
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
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    @Override
    public String toString(){
        if(this.extraCheese == false || this.extraSauce == false){
            return "[Build Your Own]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString().replace(",","") + extraSauceString().replace(",","") + ": " + "$" + price();
        }
        return "[Build Your Own]" + "[" + getSizeAsString() + "]" + "[" + getSauceAsString() + "]: " + getToppingsAsString() + extraCheeseString() + extraSauceString() + ": " + "$" + price();
    }
}
