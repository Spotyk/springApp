package com.edu.domain.model;

import com.edu.domain.entity.OrderStatus;

import javax.validation.constraints.NotNull;

public class OrderStatusModel {

    @NotNull
    private Long orderId;

    @NotNull
    private OrderStatus orderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
