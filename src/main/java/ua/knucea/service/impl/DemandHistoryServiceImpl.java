package ua.knucea.service.impl;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.knucea.domain.entity.DemandHistory;
import ua.knucea.domain.entity.RiskLevel;
import ua.knucea.domain.entity.OrderDetails;
import ua.knucea.domain.entity.ProductDemand;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.repository.DemandHistoryRepository;
import ua.knucea.repository.LanguageRepository;
import ua.knucea.repository.OrderDetailsRepository;
import ua.knucea.repository.ProductDemandRepository;
import ua.knucea.service.DemandHistoryService;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class DemandHistoryServiceImpl implements DemandHistoryService {

    private final DemandHistoryRepository demandHistoryRepository;
    private final LanguageRepository languageRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductDemandRepository productDemandRepository;


    public DemandHistoryServiceImpl(final LanguageRepository languageRepository, final DemandHistoryRepository demandHistoryRepository, OrderDetailsRepository orderDetailsRepository, ProductDemandRepository productDemandRepository) {
        this.languageRepository = languageRepository;
        this.demandHistoryRepository = demandHistoryRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productDemandRepository = productDemandRepository;
    }

    @Override
    public List<DemandHistory> findAll() {
        return demandHistoryRepository.findAll();
    }

    @Override
    @Transactional
    public void createDemandHistory() {
        DemandHistory demandHistory = new DemandHistory();
        demandHistory.setDemandDate(new Timestamp(System.currentTimeMillis()));
        demandHistoryRepository.save(demandHistory);

        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();

        orderDetailsList.stream().distinct().forEach(details -> {
            ProductEntity currentProduct = details.getProduct();
            Integer totalOrderQuantity = orderDetailsList.stream()
                    .filter(detail -> detail.getProduct().getId().equals(currentProduct.getId()))
                    .mapToInt(OrderDetails::getQuantity)
                    .sum();
            ProductDemand productDemand = new ProductDemand();
            productDemand.setProduct(currentProduct);
            productDemand.setLevel(computeDemandLevel(currentProduct, totalOrderQuantity));
            productDemand.setDemandHistory(demandHistory);
            productDemandRepository.save(productDemand);
        });


    }

    @Override
    public List<ProductDemand> findFirstByOrderByDemandDateDesc(){
        Long demandHistoryId = demandHistoryRepository
                .findFirstByOrderByDemandDateDesc()
                .orElse(new DemandHistory())
                .getId();
        return demandHistoryId == null ? Collections.emptyList(): productDemandRepository.findAllByDemandHistoryId(demandHistoryId);
    }

    private RiskLevel computeDemandLevel(ProductEntity product, Integer totalOrderQuantity) {
        double level = (double) (product.getQuantity() / totalOrderQuantity);

        if (level <= 1) {
            return RiskLevel.RED;
        }
        if (level <= 5) {
            return RiskLevel.YELLOW;
        }

        return RiskLevel.GREEN;
    }


}
