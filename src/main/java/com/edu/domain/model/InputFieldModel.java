package com.edu.domain.model;

import javax.validation.constraints.NotNull;

public class InputFieldModel {

    @NotNull
    private String inputName;

    @NotNull
    private String inputFieldValue;

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputFieldValue() {
        return inputFieldValue;
    }

    public void setInputFieldValue(String inputFieldValue) {
        this.inputFieldValue = inputFieldValue;
    }
}
