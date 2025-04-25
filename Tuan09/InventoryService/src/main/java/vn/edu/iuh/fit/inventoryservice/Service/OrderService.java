/*
 * @ (#) OrderService.java       1.0     4/19/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.inventoryservice.Service;
/*
 * @author: Luong Tan Dat
 * @date: 4/19/2025
 */

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;
    private final RateLimiter rateLimiter;
    private final TimeLimiter timeLimiter;
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;
    private static final String PAYMENT_SERVICE_URL = "http://localhost:8080/api/payments/process";

    public OrderService(RestTemplate restTemplate, CircuitBreaker circuitBreaker, Retry retry,
                        RateLimiter rateLimiter, TimeLimiter timeLimiter) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
        this.retry = retry;
        this.rateLimiter = rateLimiter;
        this.timeLimiter = timeLimiter;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down ExecutorService");
        executorService.shutdown();
    }

    public String processOrderWithCircuitBreaker(String paymentId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId=" + paymentId;
        log.debug("Calling PaymentService URL: {}", url);
        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            String result = restTemplate.postForObject(url, null, String.class);
            log.info("Circuit breaker call succeeded for paymentId: {}", paymentId);
            return result;
        });
        try {
            return supplier.get();
        } catch (Exception e) {
            log.error("Circuit breaker call failed for paymentId: {}. Error: {}", paymentId, e.getMessage());
            throw new RuntimeException("Payment processing failed: " + e.getMessage(), e);
        }
    }

    public String processOrderWithRetry(String paymentId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId=" + paymentId;
        log.debug("Calling PaymentService URL: {}", url);
        Supplier<String> supplier = Retry.decorateSupplier(retry, () -> {
            String result = restTemplate.postForObject(url, null, String.class);
            log.info("Retry call succeeded for paymentId: {}", paymentId);
            return result;
        });
        try {
            return supplier.get();
        } catch (Exception e) {
            log.error("Retry call failed for paymentId: {}. Error: {}", paymentId, e.getMessage());
            throw new RuntimeException("Payment processing failed after retries: " + e.getMessage(), e);
        }
    }

    public String processOrderWithRateLimiter(String paymentId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId=" + paymentId;
        log.debug("Calling PaymentService URL: {}", url);
        Supplier<String> supplier = RateLimiter.decorateSupplier(rateLimiter, () -> {
            String result = restTemplate.postForObject(url, null, String.class);
            log.info("Rate limiter call succeeded for paymentId: {}", paymentId);
            return result;
        });
        try {
            return supplier.get();
        } catch (Exception e) {
            log.error("Rate limiter call failed for paymentId: {}. Error: {}", paymentId, e.getMessage());
            throw new RuntimeException("Payment processing failed: " + e.getMessage(), e);
        }
    }

    public CompletableFuture<String> processOrderWithTimeLimiter(String paymentId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId=" + paymentId;
        log.debug("Calling PaymentService URL: {}", url);

        Supplier<CompletableFuture<String>> futureSupplier = () ->
            CompletableFuture.supplyAsync(() -> {
                String result = restTemplate.postForObject(url, null, String.class);
                log.info("Time limiter call succeeded for paymentId: {}", paymentId);
                return result;
            }, executorService);

        CompletableFuture<String> future = (CompletableFuture<String>) TimeLimiter.decorateFutureSupplier(timeLimiter, futureSupplier);

        return future.exceptionally(throwable -> {
            log.error("Time limiter call failed for payment4j.ratelimiter.RequestNotPermittedException");
            log.error("Time limiter call failed for paymentId: {}. Error: {}", paymentId, throwable.getMessage());
            throw new RuntimeException("Payment processing timed out: " + throwable.getMessage(), throwable);
        });
    }
}
