package com.edu.service.impl;

import com.edu.domain.entity.Role;
import com.edu.domain.entity.User;
import com.edu.domain.model.UserModel;
import com.edu.domain.model.admin.UserUpdateForm;
import com.edu.repository.UserRepository;
import com.edu.service.UserService;
import com.edu.util.FileSaver;
import com.edu.util.UserExtractorFromDTO;
import com.edu.util.Validator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.edu.command.constant.Constants.DEFAULT_CANADA_STATE;
import static com.edu.command.constant.Constants.DEFAULT_USA_STATE;
import static com.edu.command.constant.Constants.USA;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserExtractorFromDTO userExtractorFromDTO;

    public UserServiceImpl(final UserRepository userRepository, final UserExtractorFromDTO userExtractorFromDTO) {
        this.userRepository = userRepository;
        this.userExtractorFromDTO = userExtractorFromDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean addUser(UserModel model, Role role) throws IOException {
        if (userRepository.findByEmail(model.getEmail()) != null) {
            return false;
        }

        User newUser = userExtractorFromDTO.extractIntoUser(model);

        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(role));
        userRepository.save(newUser);
        return true;
    }

    @Override
    public User findPasswordByUserId(Long userId) {
        return userRepository.findPasswordById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean updateUsername(User user, String userName, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isUsernameValid(userName)) {
            error.add(".usernameError");
            return false;
        }

        User userFromBd = findByEmail(user.getEmail());
        userFromBd.setUsername(userName);
        user.setUsername(userName);

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public boolean updateEmail(User user, String email, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isEmailValid(email)) {
            error.add(".emailError");
            return false;
        }

        User userFromBd = findByEmail(user.getEmail());
        userFromBd.setEmail(email);
        user.setEmail(email);

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public boolean updateState(User user, String state, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isStateValid(state)) {
            error.add(".stateError");
            return false;
        }

        User userFromBd = findByEmail(user.getEmail());
        userFromBd.setState(state);
        user.setState(state);

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public boolean updateCountry(User user, String country, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isCountryValid(country)) {
            error.add(".countryError");
            return false;
        }

        User userFromBd = findByEmail(user.getEmail());
        userFromBd.setCountry(country);
        user.setCountry(country);

        if (country.equals(USA)) {
            userFromBd.setState(DEFAULT_USA_STATE);
            user.setState(DEFAULT_USA_STATE);
        } else {
            userFromBd.setState(DEFAULT_CANADA_STATE);
            user.setState(DEFAULT_CANADA_STATE);
        }

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public boolean updateBirthDate(User user, String birthDate, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isDateValid(birthDate)) {
            error.add(".birthDateError");
            return false;
        }

        LocalDate inputDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        User userFromBd = findByEmail(user.getEmail());
        userFromBd.setBirthDate(inputDate);
        user.setBirthDate(inputDate);

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public boolean updatePassword(User user, String password, List<String> error) {
        Validator validator = new Validator();
        if (!validator.isPasswordValid(password)) {
            error.add(".passwordError");
            return false;
        }

        User userFromBd = findByEmail(user.getEmail());

        userFromBd.setPassword(userExtractorFromDTO.encodePassword(password));
        user.setPassword(userExtractorFromDTO.encodePassword(password));

        userRepository.save(userFromBd);

        return true;
    }

    @Override
    public String updateAvatarPath(MultipartFile avatar, String uploadPath, User user) throws IOException {
        String storagePath = new FileSaver().saveFile(avatar, uploadPath);

        if (storagePath == null || storagePath.isEmpty()) {
            return "";
        }

        User userFromBd = findByEmail(user.getEmail());

        userFromBd.setAvatarPath(storagePath);
        user.setAvatarPath(storagePath);

        userRepository.save(userFromBd);
        return storagePath;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean updateUserByAdmin(UserUpdateForm form) {
        Optional<User> optionalUserFromBD = userRepository.findById(form.getId());

        if (!optionalUserFromBD.isPresent()) {
            return false;
        }

        User userFromBd = optionalUserFromBD.get();

        String currentEmail = userFromBd.getEmail();
        String newEmail = form.getEmail();


        if (findByEmail(newEmail) != null && !currentEmail.equals(newEmail)) {
            return false;
        }

        userFromBd.setUsername(form.getUsername());
        userFromBd.setState(form.getState());
        userFromBd.setCountry(form.getCountry());
        userFromBd.setEmail(form.getEmail());

        userRepository.save(userFromBd);

        return true;
    }
}
