package com.edu.util;

import com.edu.domain.User;
import com.edu.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class UserExtractorFromDTO {
    private PasswordEncoder encoder;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserExtractorFromDTO(PasswordEncoder encoder) {
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
            extractedFromDtoUser.setAvatarPath(saveFile(avatar, uploadPath));
        }

        return extractedFromDtoUser;
    }

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public String saveFile(MultipartFile file, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFileName));

        return resultFileName;
    }
}
