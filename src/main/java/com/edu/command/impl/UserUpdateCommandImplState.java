package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

import java.util.List;

public class UserUpdateCommandImplState implements UpdateUserCommand {

    private List<String> errors;

    private UserService userService;

    public UserUpdateCommandImplState(List<String> errors, UserService userService) {
        this.errors = errors;
        this.userService = userService;
    }

    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return userService.updateState(userBeforeChanges, inputValue, errors);
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
