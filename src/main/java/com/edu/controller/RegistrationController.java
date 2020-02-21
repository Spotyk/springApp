package com.edu.controller;

import com.edu.domain.model.impl.RegistrationFormUserModel;
import com.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(final UserService userService) {
        this.userService = userService;
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

        if (!userService.addUser(registrationFormUserModel)) {
            model.addAttribute("emailError", "Email exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
