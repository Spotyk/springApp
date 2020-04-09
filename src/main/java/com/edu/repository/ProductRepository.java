package com.edu.repository;

import com.edu.domain.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//    Product findByName(String name);

    List<ProductEntity> findByCategoryId(long id);

}
