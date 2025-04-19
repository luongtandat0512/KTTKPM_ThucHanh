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
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Service

public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final Retry retry;
    private final RateLimiter rateLimiter;
    private final TimeLimiter timeLimiter;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final RestTemplate restTemplate;
    private static final String PAYMENT_SERVICE_URL = "http://localhost:8080/api/payments/process";


    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(100))
                .build();
        retry = Retry.of("orderServiceRetry", retryConfig);

        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                .limitForPeriod(10)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(2))
                .build();

        rateLimiter = RateLimiter.of("orderServiceRateLimiter", rateLimiterConfig);

        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
        timeLimiter = TimeLimiter.of("orderServiceTimeLimiter", timeLimiterConfig);
    }

    // Phuong thuc su dung Circuit Breaker
    public String processOrderWithCircuitBreaker(String orderId) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(100)
                .waitDurationInOpenState(Duration.ofMillis(10))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(5)
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of("orderServiceCircuitBreaker", circuitBreakerConfig);

        String url = PAYMENT_SERVICE_URL + "?paymentId= " + orderId;
        log.debug("Calling PaymentService URL: {}", url);
        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, () ->
        {
            String result = restTemplate.postForObject(url, null, String.class);
            log.info("Circuit breaker call succeeded for orderId: {}", orderId);
            return result;
        });
        try {
            String result = supplier.get();
            log.info("Circuit breaker call result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Circuit breaker call failed for orderId: {}. Error: {}", orderId, e.getMessage());
            throw new RuntimeException("Payment processing failed", e);
        }
    }

    // Phuong thuc su dung Retry
    public String processOrderWithRetry(String orderId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId= " + orderId;
        Supplier<String> supplier = Retry.decorateSupplier(retry, () ->
                restTemplate.postForObject(url, null, String.class));
        return supplier.get();
    }

    // Phuong thuc su dung Rate Limiter
    public String processOrderWithRateLimiter(String orderId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId= " + orderId;
        Supplier<String> supplier = RateLimiter.decorateSupplier(rateLimiter, () ->
                restTemplate.postForObject(url, null, String.class));
        return supplier.get();
    }

    // Phuong thuc su dung Time Limiter
    public CompletableFuture<String> processOrderWithTimeLimiter(String orderId) {
        String url = PAYMENT_SERVICE_URL + "?paymentId= " + orderId;
        Supplier<CompletableFuture<String>> futureSupplier = (Supplier<CompletableFuture<String>>) TimeLimiter.decorateFutureSupplier(
                timeLimiter,
                () -> CompletableFuture.supplyAsync(() ->
                        restTemplate.postForObject(url, null, String.class), executorService)
        );
        return futureSupplier.get();
    }
}
