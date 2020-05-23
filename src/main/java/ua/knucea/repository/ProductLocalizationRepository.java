package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.domain.entity.product.ProductLocalization;

import java.util.List;

public interface ProductLocalizationRepository extends JpaRepository<ProductLocalization, Long> {

    long deleteByProductId(Long id);

    ProductEntity findByName(String name);

    ProductLocalization findByProductIdAndLanguageId(Long productId, Long languageId);

    List<ProductLocalization> findAllByLanguageId(Long id);
}
