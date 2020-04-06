package com.edu.domain.model.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LanguageUpdateModel {

    @NotNull
    @Size(min = 2, max = 50, message = "Language name should not be less than 2 symbols and should`nt be longer than 50")
    private String languageName;

    @NotNull
    @Size(min = 2, max = 50, message = "Language name should not be less than 2 symbols and should`nt be longer than 50")
    private String updatedLanguageName;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getUpdatedLanguageName() {
        return updatedLanguageName;
    }

    public void setUpdatedLanguageName(String updatedLanguageName) {
        this.updatedLanguageName = updatedLanguageName;
    }
}
