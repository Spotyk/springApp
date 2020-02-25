package com.edu.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    private static final String STRING_PATTERN = "^[A-zА-я]*$";

    private static final String PASSWORD_PATTERN = "^[A-zА-я0-9]*$";

    public boolean isUsernameValid(String name) {
        return isStringValid(name);
    }

    public boolean isStateValid(String state) {
        return isStringValid(state);
    }

    public boolean isCountryValid(String state) {
        return isStringValid(state);
    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isDateValid(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private boolean isStringValid(String inputString) {
        Pattern pattern = Pattern.compile(STRING_PATTERN);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches() && inputString.length() > 2 && inputString.length() < 20;
    }

    public boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches() && password.length() > 2 && password.length() < 30;
    }
}
