package vn.edu.iuh.fit.inventoryservice;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreaker circuitBreaker() {
        return CircuitBreaker.ofDefaults("orderCircuitBreaker");
    }

    @Bean
    public Retry retry() {
        return Retry.ofDefaults("orderRetry");
    }

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.ofDefaults("orderRateLimiter");
    }

    @Bean
    public TimeLimiter timeLimiter() {
        return TimeLimiter.ofDefaults("orderTimeLimiter");
    }
}
