package com.edu.controller;

import com.edu.service.CategoryService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories(LocaleContextHolder.getLocale().getLanguage()));
    }
}
