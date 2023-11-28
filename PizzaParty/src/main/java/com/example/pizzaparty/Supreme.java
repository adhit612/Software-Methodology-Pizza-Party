package com.example.pizzaparty;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that manages Supreme speciality pizza.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class Supreme extends Pizza {

    private static final DecimalFormat decimalFormat = new DecimalFormat(
            "0" +
            ".00");

    /**
     * Method that creates Supreme speciality pizza.
     */
    public Supreme() {
        this.toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI, Topping.HAM, Topping.GREENPEPPER,
                Topping.ONION, Topping.BLACKOLIVE, Topping.MUSHROOM));
        this.sauce = Sauce.TOMATO;
    }

    /**
     * Method that gets the price of different sizes of pizzas.
     *
     * @return price of pizza.
     */
    @Override
    public double getSizePrice() {
        if (this.size == Size.SMALL) {
            return 15.99;
        }
        else if (this.size == Size.MEDIUM) {
            return 17.99;
        }
        else {
            return 19.99;
        }
    }

    /**
     * Method that gets the price of different sizes of pizzas.
     *
     * @return price of pizza in formatted order.
     */
    @Override
    public double price() {
        return Double.parseDouble(decimalFormat.format(getSizePrice()
                + extraCheeseAmount() + extraSauceAmount()));
    }

    /**
     * Method that gets all toppings of a pizza in string.
     *
     * @return string format of all toppings.
     */
    @Override
    public String getToppingsAsString() {
        String toppingsAsString = "";
        for (int i = 0; i < this.toppings.size(); i++) {
            if (i == this.toppings.size() - 1) {
                toppingsAsString += this.toppings.get(i).toString();
            }
            else {
                toppingsAsString += this.toppings.get(i).toString() + ", ";
            }
        }
        return toppingsAsString;
    }

    /**
     * Method that gets the toppings of a pizza.
     *
     * @return all toppings of pizza.
     */
    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Method that gets all information about a pizza.
     *
     * @return String of pizza order full.
     */
    @Override
    public String toString() {
        return "[Supreme]" + "[" + getSizeAsString() + "]" + "[" +
                getSauceAsString() + "]: " + getToppingsAsString() +
                extraCheeseString() + extraSauceString() + ": " + "$" +
                price();
    }
}
