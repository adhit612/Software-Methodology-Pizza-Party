package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.Order;
import com.example.pizzaparty.Pizza;
import com.example.pizzaparty.StoreOrders;

import java.util.ArrayList;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    private Order order;
    private StoreOrders storeOrders;

    public static DataSingleton getInstance(){
        return instance;
    }

    public void initializeOrder(ArrayList<Pizza> pizzaList){
        order = new Order(pizzaList);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order newOrder){
        order = newOrder;
    }

    public void initializeStoreOrders(ArrayList <Order> ordersList){
        storeOrders = new StoreOrders(ordersList);
    }

    public StoreOrders getStoreOrders(){
        return storeOrders;
    }

    public void setStoreOrders(StoreOrders newStoreOrder){
        storeOrders = newStoreOrder;
    }

}
