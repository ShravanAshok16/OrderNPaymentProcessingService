package com.zero.orderprocessingservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseVO {
    private String orderId;
    private String paymentStatus;
    private String paymentReferenceNumber;
}
