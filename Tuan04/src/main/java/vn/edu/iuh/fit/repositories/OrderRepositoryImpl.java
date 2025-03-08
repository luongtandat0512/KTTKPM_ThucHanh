/*
 * @ (#) OrderRepositoryImpl.java       1.0     3/8/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;
/*
 * @author: Luong Tan Dat
 * @date: 3/8/2025
 */

import vn.edu.iuh.fit.entities.Order;
import vn.edu.iuh.fit.enums.OrderState;

public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public void processOrder(Order order) {
        order.setState(OrderState.PROCESSING);
    }

    @Override
    public void cancelOrder(Order order) {
        order.setState(OrderState.CANCEL);
    }

    @Override
    public void doneOrder(Order order) {
        order.setState(OrderState.DONE);
    }
}
