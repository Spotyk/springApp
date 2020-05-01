package ua.knucea.repository;

import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.domain.entity.product.ProductLocalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLocalizationRepository extends JpaRepository<ProductLocalization, Long> {

    ProductEntity findByName(String name);

    ProductLocalization findByProductIdAndLanguageId(Long productId, Long languageId);

    List<ProductLocalization> findAllByLanguageId(Long id);
}
