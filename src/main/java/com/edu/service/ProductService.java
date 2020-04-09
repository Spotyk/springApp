package com.edu.service;

import com.edu.domain.entity.product.ProductEntity;
import com.edu.domain.entity.product.ProductLocalization;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.admin.ProductModel;
import com.edu.domain.model.admin.ProductUpdateModel;
import com.edu.domain.model.dto.Product;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

    boolean addProduct(ProductCreationModel productCreationModel) throws IOException;

    void updateProduct(ProductUpdateModel model) throws IOException;

    List<Product> getProductsById(Set ids, String langName);

    List<Product> getAllProducts(String languageName);

    List<Product> getProductsByCategoryName(String categoryName, String langName);

    ProductEntity findById(Long id);

    ProductModel getProductEntityByProductIdAndLanguageName(Long id, String langName);

    ProductLocalization getProductByProductIdAndLanguageName(Long id, String langName);
}
