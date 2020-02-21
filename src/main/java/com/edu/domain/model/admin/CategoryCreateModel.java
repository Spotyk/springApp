package com.edu.domain.model.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryCreateModel {
    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
