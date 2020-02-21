package com.edu.domain.model.admin;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductCreationModel {
    @NotNull
    @Size(min = 2, max = 50, message = "Product name should not be less than 2 symbols and should`nt be longer than 50")
    private String productName;

    @NotNull
    @Size(min = 2, max = 50, message = "Category name should not be less than 2 symbols and should`nt be longer than 50")
    private String categoryName;

    @NotNull
    private String description;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int quantity;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int price;

    @NotNull
    private MultipartFile productImg;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
