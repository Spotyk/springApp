package com.edu.domain.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface UserModel {
    String getPassword();

    String getUsername();

    LocalDate getBirthDate();

    String getCountry();

    String getEmail();

    String getState();

    MultipartFile getAvatar();

}
