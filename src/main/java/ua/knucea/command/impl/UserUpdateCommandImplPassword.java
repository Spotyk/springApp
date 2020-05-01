package ua.knucea.command.impl;

import ua.knucea.command.UpdateUserCommand;
import ua.knucea.domain.entity.User;
import ua.knucea.service.UserService;

import java.util.List;

public class UserUpdateCommandImplPassword implements UpdateUserCommand {

    private List<String> errors;

    private UserService userService;

    public UserUpdateCommandImplPassword(List<String> errors, UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String password, User userBeforeChanges) {
        return userService.updatePassword(userBeforeChanges, password, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }
}
