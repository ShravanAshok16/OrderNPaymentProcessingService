package com.zero.paymentprocessingservice.controller;

import com.zero.paymentprocessingservice.model.PaymentResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.zero.paymentprocessingservice.constants.PaymentConstants.PAYMENT_SUCCESS;

@RestController
public class PaymentProcessingController {
    @GetMapping("/v1/payment/status")
    public ResponseEntity<PaymentResponseVO> getPaymentStaus(@RequestParam String orderId){
        return ResponseEntity.ok(PaymentResponseVO
                .builder()
                .orderId(orderId)
                .paymentStatus(PAYMENT_SUCCESS)
                .paymentReferenceNumber(UUID.randomUUID().toString())
                .build());
    }
}
