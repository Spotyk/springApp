package com.edu.controller;

import com.edu.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAllLanguages")
    public ResponseEntity<?> getLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }
}
