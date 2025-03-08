package vn.edu.iuh.fit;

import vn.edu.iuh.fit.entities.Order;
import vn.edu.iuh.fit.enums.OrderState;
import vn.edu.iuh.fit.repositories.OrderRepository;
import vn.edu.iuh.fit.repositories.OrderRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        Order order = new Order(1, "Order 1", 10, 1000, OrderState.NEW);

        System.out.println("Order 1: " + order.getState().name());

        orderRepository.processOrder(order);

        System.out.println("Order 1: " + order.getState().name());

        orderRepository.doneOrder(order);

        System.out.println("Order 1: " + order.getState().name());

        Order order2 = new Order(2, "Order 2", 10, 1000, OrderState.NEW);
        System.out.println("Order 2: " + order2.getState().name());

        orderRepository.cancelOrder(order2);

        System.out.println("Order 2: " + order2.getState().name());
    }
}