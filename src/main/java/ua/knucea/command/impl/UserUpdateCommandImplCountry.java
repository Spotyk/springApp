package ua.knucea.command.impl;

import ua.knucea.command.UpdateUserCommand;
import ua.knucea.domain.entity.User;
import ua.knucea.service.UserService;

import java.util.List;

public class UserUpdateCommandImplCountry implements UpdateUserCommand {

    private final UserService userService;
    private List<String> errors;

    public UserUpdateCommandImplCountry(List<String> errors, final UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return userService.updateCountry(userBeforeChanges, inputValue, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
