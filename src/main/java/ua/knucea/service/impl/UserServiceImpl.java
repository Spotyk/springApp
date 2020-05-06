package ua.knucea.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.knucea.command.constant.Constants;
import ua.knucea.domain.entity.Role;
import ua.knucea.domain.entity.User;
import ua.knucea.domain.model.UserModel;
import ua.knucea.domain.model.admin.UserUpdateForm;
import ua.knucea.exception.UserNotExistsException;
import ua.knucea.repository.UserRepository;
import ua.knucea.service.UserService;
import ua.knucea.util.FileSaver;
import ua.knucea.util.UserExtractorFromDTO;
import ua.knucea.util.Validator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        if (country.equals(Constants.USA)) {
            userFromBd.setState(Constants.DEFAULT_USA_STATE);
            user.setState(Constants.DEFAULT_USA_STATE);
        } else {
            userFromBd.setState(Constants.DEFAULT_CANADA_STATE);
            user.setState(Constants.DEFAULT_CANADA_STATE);
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

        User userFromBd = optionalUserFromBD.orElseThrow(() -> new UserNotExistsException("User doesnt exist."));

        String currentEmail = userFromBd.getEmail();
        String newEmail = form.getEmail();


        if (findByEmail(newEmail) != null && !currentEmail.equals(newEmail)) {
            return false;
        }

        userFromBd.setUsername(form.getUsername());
        userFromBd.setState(form.getState());
        userFromBd.setCountry(form.getCountry());
        userFromBd.setEmail(form.getEmail());
        userFromBd.setActive(isActive(form.getStatus()));

        userRepository.save(userFromBd);

        return true;
    }

    private boolean isActive(String activeStatus) {
        return activeStatus.equals("active");
    }
}
