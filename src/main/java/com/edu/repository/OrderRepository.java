package com.edu.repository;

import com.edu.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = "SELECT SUM(product.price * order_details.quantity) FROM ((order_details " +
                    "INNER JOIN ordr " +
                    "ON order_details.order_id = ordr.id) " +
                    "INNER JOIN product " +
                    "ON order_details.product_id = product.id) " +
                    "Where ordr.id = :orderId",
            nativeQuery = true)
    Long orderTotalSum(@Param("orderId") Long orderId);

    List<Order> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE ordr " +
            "SET total_sum = :sum " +
            "WHERE id = :id",
            nativeQuery = true)
    void setTotalSum(@Param("sum") Long totalSum, @Param("id") Long orderId);
}
