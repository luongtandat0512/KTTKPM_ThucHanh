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


import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String processPayment(String paymentId){
        if(Math.random() > 0.5){
            throw new RuntimeException("Payment failed");
        }
        try{
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Payment processing interrupted", e);
        }
        return "Payment processed successfully for ID: " + paymentId;
    }
}
