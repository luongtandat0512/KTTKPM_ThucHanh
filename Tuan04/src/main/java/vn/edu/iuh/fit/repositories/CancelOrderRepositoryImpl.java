/*
 * @ (#) CancelOrderRepositoryImpl.java       1.0     3/8/2025
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

public class CancelOrderRepositoryImpl implements OrderRepository{

    @Override
    public void processOrder(Order order) {
        order.setState(OrderState.CANCEL);
        System.out.println("Processing order: " + order.getName());
    }
}
