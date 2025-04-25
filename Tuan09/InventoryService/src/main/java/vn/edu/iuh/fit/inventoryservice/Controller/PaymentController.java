/*
 * @ (#) PaymentController.java       1.0     4/19/2025
 *
 * Copyright (c) 2025. All rights reserved.
 */

package vn.edu.iuh.fit.inventoryservice.Controller;
/*
 * @author: Luong Tan Dat
 * @date: 4/19/2025
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.inventoryservice.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping("/process")
    public ResponseEntity<String> processPayment(String paymentId) {
        log.info("Processing payment for paymentId: {}", paymentId);
        if (paymentId == null || paymentId.trim().isEmpty()) {
            log.error("Invalid paymentId: null or empty");
            return ResponseEntity.badRequest().body("Payment ID cannot be null or empty");
        }
        try {
            String response = paymentService.processPayment(paymentId);
            log.info("Payment processed successfully for paymentId: {}", paymentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing payment for paymentId: {}. Error: {}", paymentId, e.getMessage());
            return ResponseEntity.badRequest().body("Error processing payment: " + e.getMessage());
        }
    }
}
