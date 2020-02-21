package com.edu.controller;

import com.edu.service.ProductService;
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
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductsByCategory(@RequestParam(name = "name") String categoryName) {
        return ResponseEntity.ok(productService.getProductsByCategoryName(categoryName));
    }

}
