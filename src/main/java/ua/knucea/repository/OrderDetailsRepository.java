package ua.knucea.repository;

import ua.knucea.domain.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByOrderId(Long id);
}
