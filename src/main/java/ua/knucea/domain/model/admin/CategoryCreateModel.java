package ua.knucea.domain.model.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryCreateModel {

    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryNameRu;

    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryNameEn;

    public String getCategoryNameRu() {
        return categoryNameRu;
    }

    public void setCategoryNameRu(String categoryNameRu) {
        this.categoryNameRu = categoryNameRu;
    }

    public String getCategoryNameEn() {
        return categoryNameEn;
    }

    public void setCategoryNameEn(String categoryNameEn) {
        this.categoryNameEn = categoryNameEn;
    }
}
