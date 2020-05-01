package ua.knucea.service;

import ua.knucea.domain.entity.Order;
import ua.knucea.domain.entity.User;
import ua.knucea.domain.model.OrderStatusModel;

import java.util.List;

public interface OrderService {
    Long createOrder(User currentUser);

    void setOrderTotalSum(Long orderId);

    List<Order> findAllOrdersByUserId(Long id);

    List<Order> findAll();

    boolean changeStatus(OrderStatusModel model);
}
