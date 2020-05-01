package ua.knucea.repository;

import ua.knucea.domain.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

}
