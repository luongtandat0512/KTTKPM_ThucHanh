/*
 * @ (#) PaymentService.java       1.0     4/19/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.inventoryservice.Service;
/*
 * @author: Luong Tan Dat
 * @date: 4/19/2025
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public String processPayment(String paymentId) {
        log.info("Processing payment for paymentId: {}", paymentId);
        if (paymentId == null || paymentId.trim().isEmpty()) {
            log.error("Invalid paymentId: null or empty");
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
        if (Math.random() > 0.5) {
            log.error("Simulated payment failure for paymentId: {}", paymentId);
            throw new RuntimeException("Payment failed");
        }
        try {
            long delay = (long) (Math.random() * 3000);
            log.debug("Simulating processing delay of {}ms for paymentId: {}", delay, paymentId);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            log.error("Payment processing interrupted for paymentId: {}", paymentId);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Payment processing interrupted", e);
        }
        log.info("Payment processed successfully for paymentId: {}", paymentId);
        return "Payment processed successfully for ID: " + paymentId;
    }
}
