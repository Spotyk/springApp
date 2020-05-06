package ua.knucea.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.knucea.exception.UserNotExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserNotExistsException.class)
    public String userNotExistHandler(UserNotExistsException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "customError";
    }
}
