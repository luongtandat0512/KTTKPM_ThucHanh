/*
 * @ (#) Investor.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit;
/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */

import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject{
    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price){
        this.price = price;
        notify("The price of " + name + " has changed to " + price);
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for(Observer observer : observers){
            observer.update(message);
        }
    }
}
