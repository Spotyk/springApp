package ua.knucea.repository;

import ua.knucea.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = "SELECT SUM(product_entity.price * order_details.quantity) FROM ((order_details " +
                    "INNER JOIN ordr " +
                    "ON order_details.order_id = ordr.id) " +
                    "INNER JOIN product_entity " +
                    "ON order_details.product_id = product_entity.id) " +
                    "Where ordr.id = :orderId",
            nativeQuery = true)
    Long orderTotalSum(@Param("orderId") Long orderId);

    List<Order> findAllByUserId(Long userId);

}
