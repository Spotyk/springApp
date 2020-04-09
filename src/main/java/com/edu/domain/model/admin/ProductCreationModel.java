package com.edu.domain.model.admin;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductCreationModel {

    @NotNull
    @Size(min = 2, max = 50, message = "Product name should not be less than 2 symbols and should`nt be longer than 50")
    private String productNameRu;

    @NotNull
    @Size(min = 2, max = 50, message = "Product name should not be less than 2 symbols and should`nt be longer than 50")
    private String productNameEn;

    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryName;

    @NotNull
    private String descriptionRu;

    @NotNull
    private String descriptionEn;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int quantity;

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int price;

    @NotNull
    private MultipartFile productImg;

    public String getProductNameRu() {
        return productNameRu;
    }

    public void setProductNameRu(String productNameRu) {
        this.productNameRu = productNameRu;
    }

    public String getProductNameEn() {
        return productNameEn;
    }

    public void setProductNameEn(String productNameEn) {
        this.productNameEn = productNameEn;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MultipartFile getProductImg() {
        return productImg;
    }

    public void setProductImg(MultipartFile productImg) {
        this.productImg = productImg;
    }
}
