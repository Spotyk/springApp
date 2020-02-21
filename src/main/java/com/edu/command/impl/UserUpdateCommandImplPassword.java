package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

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
