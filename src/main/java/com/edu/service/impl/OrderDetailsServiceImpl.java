package com.edu.service.impl;

import com.edu.domain.entity.OrderDetails;
import com.edu.domain.entity.product.ProductEntity;
import com.edu.repository.CategoryRepository;
import com.edu.repository.OrderDetailsRepository;
import com.edu.repository.OrderRepository;
import com.edu.repository.ProductRepository;
import com.edu.service.OrderDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final OrderRepository orderRepository;

    public OrderDetailsServiceImpl(final CategoryRepository categoryRepository, final ProductRepository productRepository, final OrderDetailsRepository orderDetailsRepository, final OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean createOrderDetails(Long orderId, Map<ProductEntity, Long> products) {
        for (Entry<ProductEntity, Long> item : products.entrySet()) {
            createOrderDetail(item, orderId);
        }
        return true;
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(Long id) {
        return orderDetailsRepository.findAllByOrderId(id);
    }

    private void createOrderDetail(Entry<ProductEntity, Long> item, Long orderid) {
        OrderDetails details = new OrderDetails();

        details.setProduct(item.getKey());
        details.setQuantity(item.getValue());
        details.setOrder(orderRepository.getOne(orderid));

        orderDetailsRepository.save(details);
    }
}
