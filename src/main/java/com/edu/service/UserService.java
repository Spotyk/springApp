package com.edu.service;

import com.edu.domain.entity.Role;
import com.edu.domain.entity.User;
import com.edu.domain.model.UserModel;
import com.edu.domain.model.admin.UserUpdateForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {

    boolean addUser(UserModel model, Role role) throws IOException;

    User findPasswordByUserId(Long userId);

    User findByEmail(String email);

    boolean updateUsername(User user, String userName, List<String> error);

    boolean updateEmail(User user, String email, List<String> error);

    boolean updateState(User user, String state, List<String> error);

    boolean updateCountry(User user, String country, List<String> error);

    boolean updateBirthDate(User user, String birthDate, List<String> error);

    boolean updatePassword(User user, String password, List<String> error);

    String updateAvatarPath(MultipartFile avatar, String uploadPath, User user) throws IOException;

    List<User> findAllUsers();

    boolean updateUserByAdmin(UserUpdateForm form);
}
