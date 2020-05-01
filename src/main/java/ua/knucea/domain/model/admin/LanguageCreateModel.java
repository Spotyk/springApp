package ua.knucea.domain.model.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LanguageCreateModel {
    @NotNull
    @Size(min = 2, max = 50, message = "Lang should be between 2-50 symbols")
    @Pattern(regexp = "^[A-zА-я]*$")
    private String languageName;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
