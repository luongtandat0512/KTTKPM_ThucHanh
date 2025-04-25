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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/circuit-breaker")
    @CircuitBreaker(name = "orderCircuitBreaker", fallbackMethod = "fallback")
    public ResponseEntity<String> placeOrderWithCircuitBreaker(@RequestParam String paymentId) {
        validatePaymentId(paymentId);
        String response = orderService.processOrderWithCircuitBreaker(paymentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/retry")
    @Retry(name = "orderRetry", fallbackMethod = "fallback")
    public ResponseEntity<String> placeOrderWithRetry(@RequestParam String paymentId) {
        validatePaymentId(paymentId);
        String response = orderService.processOrderWithRetry(paymentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rate-limiter")
    @RateLimiter(name = "orderRateLimiter", fallbackMethod = "fallback")
    public ResponseEntity<String> placeOrderWithRateLimiter(@RequestParam String paymentId) {
        validatePaymentId(paymentId);
        String response = orderService.processOrderWithRateLimiter(paymentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/time-limiter")
    @TimeLimiter(name = "orderTimeLimiter", fallbackMethod = "fallbackAsync")
    public CompletableFuture<ResponseEntity<String>> placeOrderWithTimeLimiter(@RequestParam String paymentId) {
        validatePaymentId(paymentId);
        return orderService.processOrderWithTimeLimiter(paymentId)
                .thenApply(ResponseEntity::ok);
    }

    private void validatePaymentId(String paymentId) {
        if (paymentId == null || paymentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
    }

    public ResponseEntity<String> fallback(String paymentId, Throwable t) {
        log.error("Fallback triggered for paymentId: {}. Error: {}", paymentId, t.getMessage());
        return ResponseEntity.status(503).body("Service unavailable: " + t.getMessage());
    }

    public CompletableFuture<ResponseEntity<String>> fallbackAsync(String paymentId, Throwable t) {
        log.error("Async fallback triggered for paymentId: {}. Error: {}", paymentId, t.getMessage());
        return CompletableFuture.completedFuture(
                ResponseEntity.status(503).body("Service unavailable: " + t.getMessage()));
    }
}
