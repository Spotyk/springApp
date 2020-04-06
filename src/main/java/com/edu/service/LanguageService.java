package com.edu.service;

import com.edu.domain.entity.Language;
import com.edu.domain.model.admin.LanguageCreateModel;
import com.edu.domain.model.admin.LanguageUpdateModel;

import java.util.List;

public interface LanguageService {
    boolean addLanguage(LanguageCreateModel model);

    boolean updateLanguage(LanguageUpdateModel model);

    List<Language> getAllLanguages();
}
