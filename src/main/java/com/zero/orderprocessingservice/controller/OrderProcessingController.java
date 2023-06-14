package com.zero.orderprocessingservice.controller;

import com.zero.orderprocessingservice.model.OrderResponseVO;
import com.zero.orderprocessingservice.model.OrderVO;
import com.zero.orderprocessingservice.service.OrderProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProcessingController {
    private OrderProcessingService service;

    public OrderProcessingController(OrderProcessingService service){
        this.service=service;
    }
    @PostMapping("/v1/order")
    public ResponseEntity<OrderResponseVO> createOrder(@RequestBody OrderVO order){
        return ResponseEntity.ok(service.createOrder(order));
    }

}
