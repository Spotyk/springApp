package ua.knucea.command.impl;

import ua.knucea.command.UpdateUserCommand;
import ua.knucea.domain.entity.User;
import ua.knucea.service.UserService;

import java.util.List;

public class UserUpdateCommandImplEmail implements UpdateUserCommand {

    private List<String> errors;

    private UserService userService;

    public UserUpdateCommandImplEmail(List<String> errors, UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return userService.updateEmail(userBeforeChanges, inputValue, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
