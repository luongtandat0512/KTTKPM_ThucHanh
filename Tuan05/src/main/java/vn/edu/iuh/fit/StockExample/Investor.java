/*
 * @ (#) Investor.java       1.0     3/15/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.StockExample;
/*
 * @author: Luong Tan Dat
 * @date: 3/15/2025
 */

import vn.edu.iuh.fit.ComposePattern.Observer;

public class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
