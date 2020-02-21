package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

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
