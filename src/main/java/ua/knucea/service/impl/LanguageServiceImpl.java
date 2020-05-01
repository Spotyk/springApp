package ua.knucea.service.impl;

import ua.knucea.domain.entity.Language;
import ua.knucea.domain.model.admin.LanguageCreateModel;
import ua.knucea.domain.model.admin.LanguageUpdateModel;
import ua.knucea.repository.LanguageRepository;
import ua.knucea.service.LanguageService;
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
    public Long countLanguages() {
        return languageRepository.count();
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
