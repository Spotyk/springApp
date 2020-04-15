package com.edu.controller;

import com.edu.service.ProductService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts(LocaleContextHolder.getLocale().getLanguage()));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductsByCategory(@RequestParam(name = "name") String categoryName) {
        return ResponseEntity.ok(productService.getProductsByCategoryName(categoryName, LocaleContextHolder.getLocale().getLanguage()));
    }

    @GetMapping("/getProductNameById")
    public ResponseEntity<?> getProductNameById(@RequestParam(name = "productId") Long productId) {
        return ResponseEntity.ok(productService.getProductByProductIdAndLanguageName(productId, LocaleContextHolder.getLocale().getLanguage()));
    }
}
