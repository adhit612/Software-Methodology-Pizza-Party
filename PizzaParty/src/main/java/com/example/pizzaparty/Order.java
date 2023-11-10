package com.example.pizzaparty;
import java.util.*;

public class Order {
    private ArrayList <Pizza> pizzas;
    private int orderNumber;

    public Order(ArrayList <Pizza> pizzas){
        this.pizzas = pizzas;
    }

    public void add(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public void remove(Pizza pizza){
        this.pizzas.remove(pizza);
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString(){
        String res = "";
        for(int i = 0; i < this.pizzas.size(); i ++){
            if(i==this.pizzas.size()-1){
                res += this.pizzas.get(i).toString();
            }
            else {
                res += this.pizzas.get(i).toString() + "\n";
            }
        }
        return res;
    }
}
