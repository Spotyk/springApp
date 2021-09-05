package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.DemandHistory;
import ua.knucea.domain.entity.ProductDemand;

import java.util.List;

public interface ProductDemandRepository extends JpaRepository<ProductDemand, Long> {
    List<ProductDemand> findAllByDemandHistoryId(long id);
}
