package com.edu.service;

import com.edu.domain.entity.OrderDetails;
import com.edu.domain.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDetailsService {
    boolean createOrderDetails(Long orderId, Map<Product, Long> products);

    List<OrderDetails> getOrderDetailsByOrderId(Long id);
}
