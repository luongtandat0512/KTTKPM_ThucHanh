/*
 * @ (#) Task.java       1.0     3/15/2025
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

public class Task implements Subject{
    private String name;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public Task(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(String status){
        this.status = status;
        notify("The status of " + name + " has changed to " + status);
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
