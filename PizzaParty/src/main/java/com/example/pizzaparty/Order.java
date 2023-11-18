package com.example.pizzaparty;
import java.text.DecimalFormat;
import java.util.*;

public class Order {
    private ArrayList <Pizza> pizzas;
    private int orderNumber;
    public static final double SALES_TAX = 0.06625;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

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

    public double getTotalPriceWithoutTax(){
        double total = 0.0;
        for(int i = 0; i < this.pizzas.size(); i ++){
            total += this.pizzas.get(i).price();
        }
        return Double.parseDouble(decimalFormat.format(total));
    }

    public double getTotalPriceWithSalesTax(){
        return Double.parseDouble(decimalFormat.format(getTotalPriceWithoutTax() + (getTotalPriceWithoutTax() * SALES_TAX)));
    }

    public double getSalesTaxOfTotal(){
        return Double.parseDouble(decimalFormat.format(getTotalPriceWithoutTax() * SALES_TAX));
    }

    public ArrayList <Pizza> getPizzaList(){
        return this.pizzas;
    }

    public void setPizzasList(ArrayList <Pizza> newList){
        this.pizzas = newList;
    }
}
