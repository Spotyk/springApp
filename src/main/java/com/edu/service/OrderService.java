package com.edu.service;

import com.edu.domain.entity.Order;
import com.edu.domain.entity.OrderStatus;
import com.edu.domain.entity.User;
import com.edu.domain.model.OrderStatusModel;
import com.edu.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Long createOrder(User currentUser) {
        Order order = new Order();
        order.setUser(currentUser);
        order.setOrderDate(LocalDate.now());
        order.setStatusSet(Collections.singleton(OrderStatus.IN_PROGRESS));

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void setOrderTotalSum(Long orderId) {
        orderRepository.getOne(orderId).setTotalSum(getTotalSumByOrderId(orderId));
    }

    private Long getTotalSumByOrderId(Long id) {
        return orderRepository.orderTotalSum(id);
    }

    public List<Order> findAllOrdersByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public boolean changeStatus(OrderStatusModel model) {
        Order order = orderRepository.findById(model.getOrderId()).orElse(null);
        if (order == null) {
            return false;
        }
        Set<OrderStatus> orderStatusSet = order.getStatusSet();
        orderStatusSet.clear();
        orderStatusSet.add(model.getOrderStatus());
        orderRepository.save(order);

        return true;
    }
}
