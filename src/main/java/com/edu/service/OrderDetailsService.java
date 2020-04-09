package com.edu.service;

import com.edu.domain.entity.OrderDetails;
import com.edu.domain.entity.product.ProductEntity;

import java.util.List;
import java.util.Map;

public interface OrderDetailsService {
    boolean createOrderDetails(Long orderId, Map<ProductEntity, Long> products);

    List<OrderDetails> getOrderDetailsByOrderId(Long id);
}
