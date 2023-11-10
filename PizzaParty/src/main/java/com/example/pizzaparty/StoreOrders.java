package com.example.pizzaparty;
import java.util.*;

public class StoreOrders {
    private ArrayList <Order> orders;
    private int currOrderNumber;
    private static int nextOrderNumber;

    public StoreOrders(ArrayList <Order> orders){
        this.currOrderNumber = 1;
        this.orders = orders;
    }

    public void add(Order order){
        order.setOrderNumber(this.currOrderNumber);
        this.orders.add(order);
        this.incrementOrderNumber();
    }

    public void remove(Order order){
        this.orders.remove(order);
    }

    public int getNextOrderNumber(){
        int maxOrderNumber = -1;
        for(int i = 0; i < this.orders.size(); i ++){
            if(this.orders.get(i).getOrderNumber() > maxOrderNumber){
                maxOrderNumber = this.orders.get(i).getOrderNumber();
            }
        }
        return maxOrderNumber+1;
    }

    public void export(){

    }

    public void incrementOrderNumber(){
        this.currOrderNumber ++;
    }


    @Override
    public String toString(){
        String res = "";
        for(int i = 0; i < this.orders.size(); i ++){
            if(i == this.orders.size()-1) {
                res += "Order number " + this.orders.get(i).getOrderNumber() + "\n";
                res += this.orders.get(i).toString();
            }
            else {
                res += "Order number " + this.orders.get(i).getOrderNumber() + "\n";
                res += this.orders.get(i).toString() + "\n";
            }
        }
        return res;
    }
}
