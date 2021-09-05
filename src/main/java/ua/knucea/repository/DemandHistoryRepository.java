package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.DemandHistory;

import java.util.Optional;

public interface DemandHistoryRepository extends JpaRepository<DemandHistory, Long> {
    Optional<DemandHistory> findFirstByOrderByDemandDateDesc();
}
