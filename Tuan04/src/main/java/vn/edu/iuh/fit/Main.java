package vn.edu.iuh.fit;

import vn.edu.iuh.fit.bai1.normal.entities.Order;
import vn.edu.iuh.fit.bai1.normal.enums.OrderState;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(OrderState.NEW);
        order.processOrder();
        order.deliverOrder();

        order = new Order(OrderState.PROCESSING);
        order.cancelOrder();
    }
}