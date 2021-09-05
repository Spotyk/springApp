package ua.knucea.service.impl;

import ua.knucea.domain.entity.OrderDetails;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.repository.CategoryRepository;
import ua.knucea.repository.OrderDetailsRepository;
import ua.knucea.repository.OrderRepository;
import ua.knucea.repository.ProductRepository;
import ua.knucea.service.OrderDetailsService;
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
        details.setQuantity(item.getValue().intValue());
        details.setOrder(orderRepository.getOne(orderid));

        orderDetailsRepository.save(details);
    }
}
