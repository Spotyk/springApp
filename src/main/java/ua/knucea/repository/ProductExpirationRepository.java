package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.ProductDemand;
import ua.knucea.domain.entity.ProductExpiration;

import java.util.List;

public interface ProductExpirationRepository extends JpaRepository<ProductExpiration, Long> {
    List<ProductExpiration> findAllByExpirationHistoryId(long id);
}
