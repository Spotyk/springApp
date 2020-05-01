package ua.knucea.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.knucea.service.UserService;
import ua.knucea.util.JwtTokenUtil;

@Controller
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userDetailsService;

    public LoginController(final AuthenticationManager authenticationManager, final JwtTokenUtil jwtTokenUtil, final UserService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @PostMapping("/authorize")
    public String signIn() {
        return "cabinet";
    }
}
