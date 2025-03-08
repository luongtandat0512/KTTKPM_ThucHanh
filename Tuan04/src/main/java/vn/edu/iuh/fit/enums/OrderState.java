/*
 * @ (#) State.java       1.0     3/8/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.enums;
/*
 * @author: Luong Tan Dat
 * @date: 3/8/2025
 */

public enum OrderState {
    NEW(1, "New"),
    PROCESSING(2, "Processing"),
    DONE(3, "Done"),
    CANCEL(4, "Cancel");

    private final int id;
    private final String name;

    OrderState(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static OrderState getOrderState(int id) {
        for (OrderState orderState : OrderState.values()) {
            if (orderState.id == id) {
                return orderState;
            }
        }
        return null;
    }

}
