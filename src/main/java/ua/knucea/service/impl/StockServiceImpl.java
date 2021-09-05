package ua.knucea.service.impl;

import org.springframework.stereotype.Service;
import ua.knucea.domain.entity.Stock;
import ua.knucea.repository.LanguageRepository;
import ua.knucea.repository.StockEntityRepository;
import ua.knucea.service.StockService;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockEntityRepository stockEntityRepository;
    private final LanguageRepository languageRepository;

    public StockServiceImpl(final LanguageRepository languageRepository, final StockEntityRepository stockRepository) {
        this.languageRepository = languageRepository;
        this.stockEntityRepository = stockRepository;
    }

    @Override
    public List<Stock> findAll() {
        return stockEntityRepository.findAll();
    }
}
