package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.category.CategoryEntity;
import ua.knucea.domain.entity.category.CategoryLocalization;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryLocalization, Long> {

    CategoryLocalization findByName(String name);

    CategoryLocalization findByCategoryEntityAndLanguageId(CategoryEntity category, Long languageId);

    List<CategoryLocalization> findAllByLanguageId(Long id);

    long deleteByCategoryEntityEquals(CategoryEntity entity);
}
