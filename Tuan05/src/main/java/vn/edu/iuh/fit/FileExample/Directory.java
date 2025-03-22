/*
 * @ (#) Directory.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.FileExample;
/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */

import vn.edu.iuh.fit.ComposePattern.Observer;
import vn.edu.iuh.fit.ComposePattern.Subject;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Subject {
    private String name;
    private List<Observer> observers = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
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
        System.out.println("Directory " + name);
        for (Observer observer : observers) {
            observer.update(null);
        }
    }
}
