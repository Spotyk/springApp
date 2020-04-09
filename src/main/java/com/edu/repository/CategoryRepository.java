package com.edu.repository;

import com.edu.domain.entity.category.CategoryEntity;
import com.edu.domain.entity.category.CategoryLocalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryLocalization, Long> {

    CategoryLocalization findByName(String name);

    CategoryLocalization findByCategoryEntityAndLanguageId(CategoryEntity category, Long languageId);

    List<CategoryLocalization> findAllByLanguageId(Long id);
}
