package ua.knucea.command.impl;

import ua.knucea.command.UpdateUserCommand;
import ua.knucea.domain.entity.User;
import ua.knucea.service.UserService;

import java.util.List;

public class UserUpdateCommandImplUsername implements UpdateUserCommand {

    private List<String> errors;

    private UserService userService;

    public UserUpdateCommandImplUsername(List<String> errors, UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String userName, User userBeforeChanges) {
        return userService.updateUsername(userBeforeChanges, userName, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
