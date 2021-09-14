package ua.knucea.service.impl;

import org.springframework.stereotype.Service;
import ua.knucea.domain.entity.ExpirationHistory;
import ua.knucea.domain.entity.ProductExpiration;
import ua.knucea.domain.entity.RiskLevel;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.repository.ExpirationHistoryRepository;
import ua.knucea.repository.LanguageRepository;
import ua.knucea.repository.ProductExpirationRepository;
import ua.knucea.repository.ProductRepository;
import ua.knucea.service.ExpirationHistoryService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ExpirationHistoryServiceImpl implements ExpirationHistoryService {

    private final ExpirationHistoryRepository expirationHistoryRepository;
    private final LanguageRepository languageRepository;
    private final ProductRepository productRepository;
    private final ProductExpirationRepository productExpirationRepository;


    public ExpirationHistoryServiceImpl(ExpirationHistoryRepository expirationHistoryRepository, LanguageRepository languageRepository, ProductRepository productRepository, ProductExpirationRepository productExpirationRepository) {
        this.expirationHistoryRepository = expirationHistoryRepository;
        this.languageRepository = languageRepository;
        this.productRepository = productRepository;
        this.productExpirationRepository = productExpirationRepository;
    }

    @Override
    public List<ExpirationHistory> findAll() {
        return expirationHistoryRepository.findAll();
    }

    @Override
    @Transactional
    public void createExpirationHistory() {
        ExpirationHistory expirationHistory = new ExpirationHistory();
        expirationHistory.setExpirationDate(new Timestamp(System.currentTimeMillis()));
        expirationHistoryRepository.save(expirationHistory);
        LocalDate today = LocalDate.now();

        productRepository
                .findByExpireDateIsNotNull()
                .forEach(productEntity -> {
                    ProductExpiration productExpiration = new ProductExpiration();
                    long daysLeft = Duration.between( today.atStartOfDay(), productEntity.getExpireDate().atStartOfDay()).toDays();

                    productExpiration.setDaysLeft(daysLeft);
                    productExpiration.setLevel(computeExpirationLevel(daysLeft));
                    productExpiration.setProduct(productEntity);
                    productExpiration.setExpirationHistory(expirationHistory);
                    productExpirationRepository.save(productExpiration);
                });
    }

    private RiskLevel computeExpirationLevel(Long days) {
        if (days <= 3) {
            return RiskLevel.RED;
        }
        if (days <= 5) {
            return RiskLevel.YELLOW;
        }

        return RiskLevel.GREEN;
    }

    @Override
    public List<ProductExpiration> findFirstByOrderByDemandDateDesc() {
        Long demandHistoryId = expirationHistoryRepository
                .findFirstByOrderByExpirationDateDesc()
                .orElse(new ExpirationHistory())
                .getId();
        return demandHistoryId == null ? Collections.emptyList() : productExpirationRepository.findAllByExpirationHistoryId(demandHistoryId);
    }


}
