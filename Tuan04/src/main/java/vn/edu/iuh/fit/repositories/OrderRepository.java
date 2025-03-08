/*
 * @ (#) OrderRepository.java       1.0     3/8/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Order;

/*
 * @author: Luong Tan Dat
 * @date: 3/8/2025
 */
public interface OrderRepository {
    void processOrder(Order order);
    void cancelOrder(Order order);
    void doneOrder(Order order);
}
