package com.edu.repository;

import com.edu.domain.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

//    CategoryLocalization findByName(String name);
}
