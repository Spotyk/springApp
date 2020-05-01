package ua.knucea.service.impl;

import ua.knucea.domain.entity.Order;
import ua.knucea.domain.entity.OrderStatus;
import ua.knucea.domain.entity.User;
import ua.knucea.domain.model.OrderStatusModel;
import ua.knucea.repository.OrderRepository;
import ua.knucea.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Long createOrder(User currentUser) {
        Order order = new Order();
        order.setUser(currentUser);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.IN_PROGRESS);

        orderRepository.save(order);
        return order.getId();
    }

    @Override
    @Transactional
    public void setOrderTotalSum(Long orderId) {
        orderRepository.getOne(orderId).setTotalSum(getTotalSumByOrderId(orderId));
    }

    private Long getTotalSumByOrderId(Long id) {
        return orderRepository.orderTotalSum(id);
    }

    @Override
    public List<Order> findAllOrdersByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean changeStatus(OrderStatusModel model) {
        Order order = orderRepository.findById(model.getOrderId()).orElse(null);
        if (order == null) {
            return false;
        }
        order.setStatus(model.getOrderStatus());
        orderRepository.save(order);

        return true;
    }
}
