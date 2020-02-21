package com.edu.service;

import com.edu.domain.entity.Category;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public boolean addCategory(CategoryCreateModel categoryCreateModel) {
        String categoryName = categoryCreateModel.getCategoryName();

        if (isCategoryNameExists(categoryName)) {
            return false;
        }

        Category newCategory = new Category();
        newCategory.setName(categoryName);

        categoryRepository.save(newCategory);
        return true;
    }

    public boolean updateCategory(CategoryUpdateModel categoryUpdateModel) {
        String categoryNewName = categoryUpdateModel.getUpdatedCategoryName();
        String categoryOldName = categoryUpdateModel.getCategoryName();

        if (isCategoryNameExists(categoryNewName) && !categoryNewName.equals(categoryOldName)) {
            return false;
        }

        Category updateCategory = findByName(categoryOldName);

        if (updateCategory == null) {
            return false;
        }

        updateCategory.setName(categoryNewName);

        categoryRepository.save(updateCategory);
        return true;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public boolean addProduct(ProductCreationModel productCreationModel) {
        Category currentCategory = findByName(productCreationModel.getCategoryName());
        if (currentCategory == null) {
            return false;
        }

        return true;
    }

    private boolean isCategoryNameExists(String categoryName) {
        return findByName(categoryName) != null;
    }
}
