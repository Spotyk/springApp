package com.edu.service;

import com.edu.domain.entity.category.CategoryLocalization;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.dto.Category;

import java.util.List;

public interface CategoryService {

    CategoryLocalization findByName(String name);

    boolean addCategory(CategoryCreateModel categoryCreateModel);

    boolean updateCategory(CategoryUpdateModel categoryUpdateModel);

    List<Category> getAllCategories(String languageName);

    boolean addProduct(ProductCreationModel productCreationModel);

}
