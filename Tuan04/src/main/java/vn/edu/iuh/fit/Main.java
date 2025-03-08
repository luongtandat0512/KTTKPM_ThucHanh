package vn.edu.iuh.fit;

import vn.edu.iuh.fit.entities.Order;
import vn.edu.iuh.fit.enums.OrderState;
import vn.edu.iuh.fit.repositories.*;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(1, "Order 1", 10, 1000, OrderState.NEW);
        OrderRepository orderRepository = new NewOrderRepositoryImpl();
        orderRepository.processOrder(order);
        System.out.println("Order state: " + order.getState().name());

        orderRepository = new ProcessingOrderRepositoryImpl();
        orderRepository.processOrder(order);
        System.out.println("Order state: " + order.getState().name());

        orderRepository = new DoneOrderRepositoryImpl();
        orderRepository.processOrder(order);
        System.out.println("Order state: " + order.getState().name());

        orderRepository = new CancelOrderRepositoryImpl();
        Order order2 = new Order(2, "Order 2", 20, 2000, OrderState.NEW);
        orderRepository.processOrder(order2);
        System.out.println("Order state: " + order2.getState().name());
        
    }
}