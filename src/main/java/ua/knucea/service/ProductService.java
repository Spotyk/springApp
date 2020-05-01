package ua.knucea.service;

import ua.knucea.domain.entity.product.ProductEntity;
import ua.knucea.domain.entity.product.ProductLocalization;
import ua.knucea.domain.model.admin.ProductCreationModel;
import ua.knucea.domain.model.admin.ProductModel;
import ua.knucea.domain.model.admin.ProductUpdateModel;
import ua.knucea.domain.model.dto.Product;

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
