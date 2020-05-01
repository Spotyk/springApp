package ua.knucea.controller;

import ua.knucea.command.UpdateCommandContainer;
import ua.knucea.command.UpdateUserCommand;
import ua.knucea.domain.entity.Role;
import ua.knucea.domain.entity.User;
import ua.knucea.domain.model.InputFieldModel;
import ua.knucea.domain.model.impl.RegistrationFormUserModel;
import ua.knucea.domain.model.impl.UpdateFormUserModel;
import ua.knucea.service.UserService;
import ua.knucea.util.UserUpdateContainerInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final PasswordEncoder encoder;

    private final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    public UserController(final PasswordEncoder encoder, final UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @GetMapping("/cabinet")
    public String cabinet() {
        return "cabinet";
    }

    @GetMapping("/updateUsername")
    public String getUserUpdatePage(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "cabinet";
        }
        model.addAttribute("user", user);
        return "userUpdate";
    }

    @PostMapping("/userUpdate")
    public String updateUser(
            @AuthenticationPrincipal User userBeforeChanges,
            @Valid UpdateFormUserModel updateFormUserModel,
            BindingResult bindingResult,
            Model model) {
        User userFromBd = userService.findPasswordByUserId(userBeforeChanges.getId());

        if (!encoder.matches(updateFormUserModel.getOldPassword(), userFromBd.getPassword())) {
            model.addAttribute("oldPasswordError", "Old pass not correct");
            return "userUpdate";
        }

        String passwordFromForm = updateFormUserModel.getPassword();
        String confirmedPasswordFromForm = updateFormUserModel.getPassword2();

        if ((passwordFromForm == null && confirmedPasswordFromForm == null)
                || !passwordFromForm.equals(confirmedPasswordFromForm)) {
            model.addAttribute("passwordError", "Passwords are different!");

            return "userUpdate";
        }

        String emailFromForm = updateFormUserModel.getEmail();

        if (userService.findByEmail(emailFromForm) != null && !userBeforeChanges.getEmail().equals(emailFromForm)) {
            model.addAttribute("emailError", "Email exists!");

            return "userUpdate";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            return "userUpdate";
        }

        return "cabinet";
    }

    @PostMapping("/cabinet")
    @ResponseBody
    public List<String> updateUser(
            @AuthenticationPrincipal User userBeforeChanges,
            @Valid InputFieldModel inputField,
            BindingResult bindingResult,
            HttpServletResponse response
    ) {
        UserUpdateContainerInitializer container = new UserUpdateContainerInitializer(userService);
        UpdateCommandContainer commandContainer = container.initCommands();

        UpdateUserCommand currentCommand = commandContainer.getCommand(inputField.getInputName());

        if (!currentCommand.update(inputField.getInputFieldValue(), userBeforeChanges)) {
            response.setStatus(400);
            return currentCommand.getErrors();
        }
        return Collections.emptyList();
    }


    @PostMapping("/updateFilePath")
    @ResponseBody
    public String updateFilePath(
            @AuthenticationPrincipal User userBeforeChanges,
            @RequestParam("filePath") MultipartFile avatarFile,
            HttpServletResponse response) throws IOException {
        if (avatarFile == null && !avatarFile.getOriginalFilename().isEmpty()) {
            response.setStatus(400);
            return "something went wrong";
        }

        String answer = userService.updateAvatarPath(avatarFile, uploadPath, userBeforeChanges);
        if (answer == null || answer.isEmpty()) {
            response.setStatus(400);
            return "something went wrong";
        }
        response.setStatus(200);
        return answer;
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Valid RegistrationFormUserModel registrationFormUserModel,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (registrationFormUserModel.getPassword() != null && !registrationFormUserModel.getPassword().equals(registrationFormUserModel.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(registrationFormUserModel, Role.USER)) {
            model.addAttribute("emailError", "Email exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
