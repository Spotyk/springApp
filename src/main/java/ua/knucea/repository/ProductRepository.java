package ua.knucea.repository;

import ua.knucea.domain.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCategoryId(long id);

    List<ProductEntity> findByExpireDateIsNotNull();

}
