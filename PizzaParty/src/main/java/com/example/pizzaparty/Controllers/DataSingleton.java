package com.example.pizzaparty.Controllers;

import com.example.pizzaparty.Order;
import com.example.pizzaparty.Pizza;
import com.example.pizzaparty.StoreOrders;

import java.util.ArrayList;

/**
 * Class that allows data to be accessed through multiple classes.
 *
 * @author Abhishek Thakare, Adhit Thakur.
 */
public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    private Order order;
    private StoreOrders storeOrders;

    private boolean orderAdded = false;

    /**
     * Method that returns the instance of singleton.
     */
    public static DataSingleton getInstance() {
        return instance;
    }

    /**
     * Method that initializes the order of pizzas.
     *
     * @param pizzaList holds all orders of pizzas.
     */
    public void initializeOrder(ArrayList<Pizza> pizzaList) {
        order = new Order(pizzaList);
    }

    /**
     * Method that handles getting the order number.
     *
     * @return order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Method that handles getting the just added order.
     *
     * @return bool value for if order is added.
     */
    public boolean getOrderAdded() {
        return orderAdded;
    }

    /**
     * Method that handles checking whether the order was added successfully.
     *
     * @param bool true or false for if order is added.
     */
    public void setOrderAdded(boolean bool) {
        orderAdded = bool;
    }

    /**
     * Method that handles setting the latest order.
     *
     * @param newOrder the latest order in the list.
     */
    public void setOrder(Order newOrder) {
        order = newOrder;
    }

    /**
     * Method that handles creating the store orders from scratch.
     *
     * @param ordersList list of orders.
     */
    public void initializeStoreOrders(ArrayList<Order> ordersList) {
        storeOrders = new StoreOrders(ordersList);
    }

    /**
     * Method that handles getting all store orders currently.
     *
     * @return storeOrder list
     */
    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    /**
     * Method that handles setting the latest store order.
     *
     * @param newStoreOrder latest store order.
     */
    public void setStoreOrders(StoreOrders newStoreOrder) {
        storeOrders = newStoreOrder;
    }

}
