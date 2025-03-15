/*
 * @ (#) OrderRepositoryImpl.java       1.0     3/8/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.bai1.state.repository.impl;
/*
 * @author: Luong Tan Dat
 * @date: 3/8/2025
 */

import vn.edu.iuh.fit.bai1.state.repository.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void processOrder() {
        System.out.println("Order processing");
    }
}
