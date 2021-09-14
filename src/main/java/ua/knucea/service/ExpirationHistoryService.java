package ua.knucea.service;

import ua.knucea.domain.entity.ExpirationHistory;
import ua.knucea.domain.entity.ProductExpiration;

import java.util.List;

public interface ExpirationHistoryService {

    List<ExpirationHistory> findAll();

    void createExpirationHistory();

    List<ProductExpiration> findFirstByOrderByDemandDateDesc();

}
