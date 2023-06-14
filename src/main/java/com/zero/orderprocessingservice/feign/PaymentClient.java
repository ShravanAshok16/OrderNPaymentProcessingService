package com.zero.orderprocessingservice.feign;

import com.zero.orderprocessingservice.model.PaymentResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-client", url = "${payment-service-url}")
public interface PaymentClient {
    @GetMapping("/v1/payment/status")
    public PaymentResponseVO getPaymentStatus(@RequestParam String orderId);
}
