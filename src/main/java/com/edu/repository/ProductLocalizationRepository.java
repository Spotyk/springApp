package com.edu.repository;

import com.edu.domain.entity.product.ProductEntity;
import com.edu.domain.entity.product.ProductLocalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLocalizationRepository extends JpaRepository<ProductLocalization, Long> {

    ProductEntity findByName(String name);

//    List<ProductLocalization> findByCategoryEntityAndLanguageId(Long categoryId, Long languageId);
    ProductLocalization findByProductIdAndLanguageId(Long productId, Long languageId);

    List<ProductLocalization> findAllByLanguageId(Long id);
}
