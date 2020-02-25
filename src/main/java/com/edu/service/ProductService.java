package com.edu.service;

import com.edu.domain.entity.Category;
import com.edu.domain.entity.Product;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.admin.ProductUpdateModel;
import com.edu.repository.CategoryRepository;
import com.edu.repository.ProductRepository;
import com.edu.util.FileSaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public ProductService(final CategoryRepository categoryRepository, final ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public boolean addProduct(ProductCreationModel productCreationModel) throws IOException {
        Category currentCategory = categoryRepository.findByName(productCreationModel.getCategoryName());

        if (currentCategory == null) {
            return false;
        }

        if (productRepository.findByName(productCreationModel.getProductName()) != null) {
            return false;
        }

        productRepository.save(extractProductFromModel(productCreationModel, currentCategory));

        return true;
    }

    public void updateProduct(ProductUpdateModel model) throws IOException {
        Product product = productRepository.getOne(model.getId());

        product.setCategory(categoryRepository.findByName(model.getCategoryName()));
        product.setQuantity(model.getQuantity());
        product.setPrice(model.getPrice());
        product.setName(model.getProductName());
        product.setDescription(model.getDescription());
        MultipartFile productImg = model.getProductImg();

        if (productImg != null) {
            FileSaver fileSaver = new FileSaver();
            product.setImgPath(fileSaver.saveFile(productImg, uploadPath));
        }

        productRepository.save(product);
    }

    private Product extractProductFromModel(ProductCreationModel model, Category currentCategory) throws IOException {
        Product newProduct = new Product();

        newProduct.setDescription(model.getDescription());
        newProduct.setName(model.getProductName());
        newProduct.setPrice(model.getPrice());
        newProduct.setQuantity(model.getQuantity());
        newProduct.setCategory(currentCategory);

        MultipartFile avatar = model.getProductImg();
        if (avatar != null && !avatar.getOriginalFilename().isEmpty()) {
            FileSaver fileSaver = new FileSaver();
            newProduct.setImgPath(fileSaver.saveFile(avatar, uploadPath));
        }

        return newProduct;
    }

    public List<Product> getProductsById(Set ids) {
        return productRepository.findAllById(ids);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        //ifvalid
        //todo
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            return Collections.emptyList();
        }
        return productRepository.findByCategoryId(category.getId());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
