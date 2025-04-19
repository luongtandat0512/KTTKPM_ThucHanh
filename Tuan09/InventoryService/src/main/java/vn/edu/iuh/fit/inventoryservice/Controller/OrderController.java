/*
 * @ (#) OrderController.java       1.0     4/19/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.inventoryservice.Controller;
/*
 * @author: Luong Tan Dat
 * @date: 4/19/2025
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.inventoryservice.Service.OrderService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/circuit-breaker")
    public ResponseEntity<String> placeOrderWithCircuitBreaker(@RequestParam String paymentId) {
        try{
            String response = orderService.processOrderWithCircuitBreaker(paymentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing order: " + e.getMessage());
        }
    }

    @PostMapping("/retry")
    public ResponseEntity<String> placeOrderWithRetry(@RequestParam String paymentId) {
        try{
            String response = orderService.processOrderWithRetry(paymentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing order: " + e.getMessage());
        }
    }

    @PostMapping("/rate-limiter")
    public ResponseEntity<String> placeOrderWithRateLimiter(@RequestParam String paymentId) {
        try{
            String response = orderService.processOrderWithRateLimiter(paymentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing order: " + e.getMessage());
        }
    }

    @PostMapping("/time-limiter")
    public ResponseEntity<CompletableFuture<String>> placeOrderWithTimeLimiter(@RequestParam String paymentId) {
        try{
            CompletableFuture<String> response = orderService.processOrderWithTimeLimiter(paymentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(CompletableFuture.completedFuture("Error processing order: " + e.getMessage()));
        }
    }

}
