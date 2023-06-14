package com.zero.orderprocessingservice.service;

import com.zero.orderprocessingservice.entity.Order;
import com.zero.orderprocessingservice.feign.PaymentClient;
import com.zero.orderprocessingservice.model.OrderResponseVO;
import com.zero.orderprocessingservice.model.OrderVO;
import com.zero.orderprocessingservice.model.PaymentResponseVO;
import com.zero.orderprocessingservice.repository.OrderProcessingRepository;
import org.springframework.stereotype.Service;

import static com.zero.orderprocessingservice.constants.OrderProcessingConstants.*;

@Service
public class OrderProcessingService {

    private OrderProcessingRepository orderProcessingRepository;
    private PaymentClient paymentClient;
    public OrderProcessingService(OrderProcessingRepository orderProcessingRepository, PaymentClient paymentClient){
        this.orderProcessingRepository=orderProcessingRepository;
        this.paymentClient=paymentClient;
    }

    public OrderResponseVO createOrder(OrderVO orderVO){
        OrderResponseVO orderResponseVO = OrderResponseVO
                .builder()
                .orderId(orderVO.getOrderId())
                .build();
        PaymentResponseVO paymentResponseVO = paymentClient.getPaymentStatus(orderVO.getOrderId());
        if(PAYMENT_SUCCESS.equals(paymentResponseVO.getPaymentStatus())){
            orderProcessingRepository.save(Order
                    .builder()
                    .orderId(orderVO.getOrderId())
                    .orderStatus(ORDER_CREATED)
                    .orderAmount(orderVO.getOrderAmount())
                    .paymentReferenceNumber(paymentResponseVO.getPaymentStatus())
                    .build());
            orderResponseVO.setOrderStatus(ORDER_CREATED);
        }else{
            orderProcessingRepository.save(Order
                    .builder()
                    .orderId(orderVO.getOrderId())
                    .orderStatus(ORDER_PROCESSING_FAILED)
                    .orderAmount(orderVO.getOrderAmount())
                    .build());
            orderResponseVO.setOrderStatus(ORDER_PROCESSING_FAILED);
        }

        return orderResponseVO;
    }
}
