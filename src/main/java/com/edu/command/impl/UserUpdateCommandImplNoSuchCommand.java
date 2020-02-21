package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.entity.User;
import com.edu.service.UserService;

import java.util.List;

public class UserUpdateCommandImplNoSuchCommand implements UpdateUserCommand {

    private final UserService userService;
    private List<String> errors;

    public UserUpdateCommandImplNoSuchCommand(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return false;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
