package ua.knucea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ua.knucea.domain.entity.Role;
import ua.knucea.domain.model.dto.CaptchaResponseDto;
import ua.knucea.domain.model.impl.RegistrationFormUserModel;
import ua.knucea.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    public RegistrationController(final RestTemplate restTemplate, final UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid RegistrationFormUserModel registrationFormUserModel,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        if (registrationFormUserModel.getPassword() != null && !registrationFormUserModel.getPassword().equals(registrationFormUserModel.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors() || !response.isSuccess()) {
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
