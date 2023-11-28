package com.example.pizzaparty;

import javafx.scene.paint.Stop;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {

    @Test
    void priceOne() {
        Pizza buildOne = new BuildYourOwn();
        buildOne.extraCheese = false;
        buildOne.size = Size.SMALL;
        buildOne.extraSauce = false;
        buildOne.toppings = new ArrayList<>();
        buildOne.toppings.add(Topping.BLACKOLIVE);
        buildOne.toppings.add(Topping.MUSHROOM);
        buildOne.toppings.add(Topping.CHICKEN);
        buildOne.sauce = Sauce.ALFREDO;
        double p = 8.99;
        assertTrue(buildOne.price() == p);
    }
    @Test
    void priceTwo() {
        Pizza buildTwo = new BuildYourOwn();
        buildTwo.extraCheese = true;
        buildTwo.size = Size.MEDIUM;
        buildTwo.extraSauce = true;
        buildTwo.toppings = new ArrayList<>();
        buildTwo.toppings.add(Topping.HOTSAUCE);
        buildTwo.toppings.add(Topping.PEPPERONI);
        buildTwo.toppings.add(Topping.GREENPEPPER);
        buildTwo.toppings.add(Topping.ONION);
        buildTwo.incrementToppingsAmount();
        buildTwo.toppings.add(Topping.SHRIMP);
        buildTwo.incrementToppingsAmount();
        buildTwo.toppings.add(Topping.SQUID);
        buildTwo.incrementToppingsAmount();
        buildTwo.toppings.add(Topping.HAM);
        buildTwo.incrementToppingsAmount();
        buildTwo.sauce = Sauce.TOMATO;
        double p = 18.95;
        assertTrue(buildTwo.price() == p);
    }
    @Test
    void priceThree() {
        Pizza buildThree = new BuildYourOwn();
        buildThree.extraCheese = false;
        buildThree.size = Size.LARGE;
        buildThree.extraSauce = false;
        buildThree.toppings = new ArrayList<>();
        buildThree.toppings.add(Topping.BEEF);
        buildThree.toppings.add(Topping.SAUSAGE);
        buildThree.toppings.add(Topping.CHICKEN);
        buildThree.toppings.add(Topping.SHRIMP);
        buildThree.incrementToppingsAmount();
        buildThree.sauce = Sauce.ALFREDO;
        double p = 14.48;
        assertTrue(buildThree.price() == p);
    }
    @Test
    void priceFour() {
        Pizza buildFour = new BuildYourOwn();
        buildFour.extraCheese = true;
        buildFour.size = Size.SMALL;
        buildFour.extraSauce = true;
        buildFour.toppings = new ArrayList<>();
        buildFour.toppings.add(Topping.ONION);
        buildFour.toppings.add(Topping.MUSHROOM);
        buildFour.toppings.add(Topping.GREENPEPPER);
        buildFour.toppings.add(Topping.BLACKOLIVE);
        buildFour.incrementToppingsAmount();
        buildFour.toppings.add(Topping.CHICKEN);
        buildFour.incrementToppingsAmount();
        buildFour.toppings.add(Topping.HOTSAUCE);
        buildFour.incrementToppingsAmount();
        buildFour.toppings.remove(Topping.ONION);
        buildFour.decrementToppingsAmount();
        buildFour.toppings.remove(Topping.MUSHROOM);
        buildFour.decrementToppingsAmount();
        buildFour.sauce = Sauce.TOMATO;
        double p = 12.48;
        assertTrue(buildFour.price() == p);
    }
    @Test
    void priceFive() {
        Pizza buildFive = new BuildYourOwn();
        buildFive.extraCheese = false;
        buildFive.size = Size.MEDIUM;
        buildFive.extraSauce = true;
        buildFive.toppings = new ArrayList<>();
        buildFive.toppings.add(Topping.CRABMEATS);
        buildFive.toppings.add(Topping.SHRIMP);
        buildFive.toppings.add(Topping.BLACKOLIVE);
        buildFive.toppings.remove(Topping.CRABMEATS);
        buildFive.toppings.add(Topping.PEPPERONI);
        buildFive.sauce = Sauce.ALFREDO;
        double p = 11.99;
        assertTrue(buildFive.price() == p);
    }
}