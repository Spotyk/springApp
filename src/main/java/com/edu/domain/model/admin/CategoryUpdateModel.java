package com.edu.domain.model.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryUpdateModel {
    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryName;

    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String updatedCategoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUpdatedCategoryName() {
        return updatedCategoryName;
    }

    public void setUpdatedCategoryName(String updatedCategoryName) {
        this.updatedCategoryName = updatedCategoryName;
    }
}
