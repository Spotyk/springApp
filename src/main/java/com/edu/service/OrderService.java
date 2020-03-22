package com.edu.service;

import com.edu.domain.entity.Order;
import com.edu.domain.entity.User;
import com.edu.domain.model.OrderStatusModel;

import java.util.List;

public interface OrderService {
    Long createOrder(User currentUser);

    void setOrderTotalSum(Long orderId);

    List<Order> findAllOrdersByUserId(Long id);

    List<Order> findAll();

    boolean changeStatus(OrderStatusModel model);
}
