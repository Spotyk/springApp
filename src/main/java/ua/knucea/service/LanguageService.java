package ua.knucea.service;

import ua.knucea.domain.entity.Language;
import ua.knucea.domain.model.admin.LanguageCreateModel;
import ua.knucea.domain.model.admin.LanguageUpdateModel;

import java.util.List;

public interface LanguageService {
    boolean addLanguage(LanguageCreateModel model);

    boolean updateLanguage(LanguageUpdateModel model);

    Long countLanguages();

    List<Language> getAllLanguages();
}
