package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.DemandHistory;
import ua.knucea.domain.entity.ExpirationHistory;

import java.util.Optional;

public interface ExpirationHistoryRepository extends JpaRepository<ExpirationHistory, Long> {
    Optional<ExpirationHistory> findFirstByOrderByExpirationDateDesc();
}
