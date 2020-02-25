package com.edu.util;

import com.edu.domain.entity.User;
import com.edu.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UserExtractorFromDTO {

    private final PasswordEncoder encoder;

    @Value("${upload.path}")
    private String uploadPath;

    public UserExtractorFromDTO(final PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User extractIntoUser(UserModel model) throws IOException {
        User extractedFromDtoUser = new User();

        extractedFromDtoUser.setPassword(encodePassword(model.getPassword()));
        extractedFromDtoUser.setUsername(model.getUsername());
        extractedFromDtoUser.setBirthDate(model.getBirthDate());
        extractedFromDtoUser.setCountry(model.getCountry());
        extractedFromDtoUser.setState(model.getState());
        extractedFromDtoUser.setEmail(model.getEmail());

        MultipartFile avatar = model.getAvatar();
        if (avatar != null && !avatar.getOriginalFilename().isEmpty()) {
            FileSaver fileSaver = new FileSaver();
            extractedFromDtoUser.setAvatarPath(fileSaver.saveFile(avatar, uploadPath));
        }

        return extractedFromDtoUser;
    }

    public String encodePassword(String password) {
        return encoder.encode(password);
    }


}
