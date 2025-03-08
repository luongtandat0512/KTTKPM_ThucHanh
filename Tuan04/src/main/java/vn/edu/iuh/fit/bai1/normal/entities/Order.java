/*
 * @ (#) Order.java       1.0     3/8/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.bai1.normal.entities;
/*
 * @author: Luong Tan Dat
 * @date: 3/8/2025
 */

import vn.edu.iuh.fit.bai1.normal.enums.OrderState;

public class Order {
    private OrderState orderState;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Order(OrderState orderState) {
        this.orderState = orderState;
    }

    public void processOrder() {
        if (orderState == OrderState.NEW) {
            orderState = OrderState.PROCESSING;
            System.out.println("Order processing");
        } else {
            System.out.println("Cannot process order");
        }
    }

    public void deliverOrder() {
        if (orderState == OrderState.PROCESSING) {
            orderState = OrderState.DONE;
            System.out.println("Order done");
        } else {
            System.out.println("Cannot deliver order");
        }
    }

    public void cancelOrder() {
        if (orderState == OrderState.NEW || orderState == OrderState.PROCESSING) {
            orderState = OrderState.CANCELLED;
            System.out.println("Order cancelled");
        } else {
            System.out.println("Cannot cancel order");
        }
    }
}
