package com.edu.service.impl;

import com.edu.domain.entity.Language;
import com.edu.domain.entity.category.CategoryEntity;
import com.edu.domain.entity.category.CategoryLocalization;
import com.edu.domain.model.admin.CategoryCreateModel;
import com.edu.domain.model.admin.CategoryUpdateModel;
import com.edu.domain.model.admin.ProductCreationModel;
import com.edu.domain.model.dto.Category;
import com.edu.repository.CategoryEntityRepository;
import com.edu.repository.CategoryRepository;
import com.edu.repository.LanguageRepository;
import com.edu.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final ModelMapper modelMapper;
    private final LanguageRepository languageRepository;

    public CategoryServiceImpl(final ModelMapper modelMapper, final CategoryRepository categoryRepository, final LanguageRepository languageRepository, final CategoryEntityRepository categoryEntityRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.categoryEntityRepository = categoryEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryLocalization findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public boolean addCategory(CategoryCreateModel categoryCreateModel) {
        String categoryNameEn = categoryCreateModel.getCategoryNameEn();
        String categoryNameRu = categoryCreateModel.getCategoryNameRu();

        if (isCategoryNameExists(categoryNameEn) || isCategoryNameExists(categoryNameRu)) {
            return false;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntityRepository.save(categoryEntity);

        CategoryLocalization categoryLocalizationRu = new CategoryLocalization();
        categoryLocalizationRu.setName(categoryNameRu);
        categoryLocalizationRu.setLanguage(languageRepository.findByName("ru"));
        categoryLocalizationRu.setCategoryEntity(categoryEntity);

        CategoryLocalization categoryLocalizationEn = new CategoryLocalization();
        categoryLocalizationEn.setName(categoryNameEn);
        categoryLocalizationEn.setLanguage(languageRepository.findByName("en"));
        categoryLocalizationEn.setCategoryEntity(categoryEntity);


        categoryRepository.save(categoryLocalizationRu);
        categoryRepository.save(categoryLocalizationEn);
        return true;
    }

    @Override
    public boolean updateCategory(CategoryUpdateModel categoryUpdateModel) {
        String categoryNewName = categoryUpdateModel.getUpdatedCategoryName();
        String categoryOldName = categoryUpdateModel.getCategoryName();

        if (isCategoryNameExists(categoryNewName) && !categoryNewName.equals(categoryOldName)) {
            return false;
        }

        CategoryLocalization updateCategory = findByName(categoryOldName);

        if (updateCategory == null) {
            return false;
        }

        updateCategory.setName(categoryNewName);

        categoryRepository.save(updateCategory);
        return true;
    }

    @Override
    public List<Category> getAllCategories(String languageName) {
        Language language = languageRepository.findByName(languageName);
        return categoryRepository.findAllByLanguageId(language.getId())
                .stream().map(this::convertToCategory)
                .collect(Collectors.toList());
    }

    private Category convertToCategory(CategoryLocalization categoryLocalization) {
        return modelMapper.map(categoryLocalization, Category.class);
    }

    private boolean isCategoryNameExists(String categoryName) {
        return findByName(categoryName) != null;
    }
}
