package ua.knucea.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ua.knucea.domain.entity.DemandHistory;
import ua.knucea.domain.entity.ProductDemand;
import ua.knucea.domain.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface DemandHistoryService {

    List<DemandHistory> findAll();
    void createDemandHistory();
    List<ProductDemand> findFirstByOrderByDemandDateDesc() throws ChangeSetPersister.NotFoundException;

}
