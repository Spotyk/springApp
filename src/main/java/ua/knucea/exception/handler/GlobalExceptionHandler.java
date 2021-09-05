package ua.knucea.exception.handler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public String userNotExistHandler(UsernameNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "customError";
    }
}
