package com.edu.domain.model.impl;

import com.edu.domain.model.UserModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UpdateFormUserModel implements UserModel {
    @NotNull
    @Size(min = 2, max = 20, message = "Name should not be less than 2 symbols")
    @Pattern(regexp = "^[A-zА-я]*$")
    private String username;

    @Pattern(regexp = "^[A-zА-я0-9]*$")
    private String password;

    @Transient
    @NotNull(message = "Password confirmation cannot be empty")
    private String password2;

    @NotNull
    @Size(min = 2, max = 200, message = "Pass should not be less than 2 symbols")
    @Pattern(regexp = "^[A-zА-я0-9]*$")
    private String oldPassword;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Size(min = 2, max = 200, message = "Country is mandatory")
    @Pattern(regexp = "^[A-zА-я]*$")
    private String country;

    @NotNull
    @Size(min = 2, max = 20, message = "State is mandatory")
    @Pattern(regexp = "^[A-zА-я]*$")
    private String state;

    @NotNull(message = "Date should be fill")
    @PastOrPresent(message = "Date should be non future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private MultipartFile avatar;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}