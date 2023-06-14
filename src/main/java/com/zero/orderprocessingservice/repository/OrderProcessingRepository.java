package com.zero.orderprocessingservice.repository;

import com.zero.orderprocessingservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderProcessingRepository extends MongoRepository<Order,String> {
}
