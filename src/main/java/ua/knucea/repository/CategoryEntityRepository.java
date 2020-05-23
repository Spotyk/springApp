package ua.knucea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knucea.domain.entity.category.CategoryEntity;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

}
