package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

import java.util.List;

public class UserUpdateCommandImplBirthDate implements UpdateUserCommand {

    private List<String> errors;

    private UserService userService;

    public UserUpdateCommandImplBirthDate(List<String> errors, UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return userService.updateBirthDate(userBeforeChanges, inputValue, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
