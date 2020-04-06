package com.edu.service.impl;

import com.edu.domain.entity.Language;
import com.edu.domain.model.admin.LanguageCreateModel;
import com.edu.domain.model.admin.LanguageUpdateModel;
import com.edu.repository.LanguageRepository;
import com.edu.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(final LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public boolean addLanguage(LanguageCreateModel model) {
        Language languageFromDb = languageRepository.findByName(model.getLanguageName());

        if (languageFromDb != null) {
            return false;
        }

        Language newLanguage = new Language();
        newLanguage.setName(model.getLanguageName());

        languageRepository.save(newLanguage);
        return true;
    }

    @Override
    public boolean updateLanguage(LanguageUpdateModel model) {
        Language languageFromDb = languageRepository.findByName(model.getLanguageName());

        if (languageFromDb == null) {
            return false;
        }

        languageFromDb.setName(model.getUpdatedLanguageName());
        languageRepository.save(languageFromDb);
        return true;
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
