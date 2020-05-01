package ua.knucea.service;

import ua.knucea.domain.entity.category.CategoryLocalization;
import ua.knucea.domain.model.admin.CategoryCreateModel;
import ua.knucea.domain.model.admin.CategoryUpdateModel;
import ua.knucea.domain.model.dto.Category;

import java.util.List;

public interface CategoryService {

    CategoryLocalization findByName(String name);

    boolean addCategory(CategoryCreateModel categoryCreateModel);

    boolean updateCategory(CategoryUpdateModel categoryUpdateModel);

    List<Category> getAllCategories(String languageName);
}
