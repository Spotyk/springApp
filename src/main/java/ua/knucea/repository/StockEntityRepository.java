package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.Stock;

public interface StockEntityRepository extends JpaRepository<Stock, Long> {

}
