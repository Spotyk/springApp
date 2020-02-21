package com.edu.repository;

import com.edu.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findByCategoryId(long id);
}
