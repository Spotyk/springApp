package ua.knucea.service;

import ua.knucea.domain.entity.OrderDetails;
import ua.knucea.domain.entity.product.ProductEntity;

import java.util.List;
import java.util.Map;

public interface OrderDetailsService {
    boolean createOrderDetails(Long orderId, Map<ProductEntity, Long> products);

    List<OrderDetails> getOrderDetailsByOrderId(Long id);
}
