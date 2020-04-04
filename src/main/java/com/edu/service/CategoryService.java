package com.edu.service;

import com.edu.domain.entity.category.Category;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;

import java.util.List;

public interface CategoryService {

    Category findByName(String name);

    boolean addCategory(CategoryCreateModel categoryCreateModel);

    boolean updateCategory(CategoryUpdateModel categoryUpdateModel);

    List<Category> getAllCategories();

    boolean addProduct(ProductCreationModel productCreationModel);

}
