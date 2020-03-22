package com.edu.service;

import com.edu.domain.entity.Product;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.admin.ProductUpdateModel;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

    boolean addProduct(ProductCreationModel productCreationModel) throws IOException;

    void updateProduct(ProductUpdateModel model) throws IOException;

    List<Product> getProductsById(Set ids);

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryName(String categoryName);

    Product findById(Long id);

}
