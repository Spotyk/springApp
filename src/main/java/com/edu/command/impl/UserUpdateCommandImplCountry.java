package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserUpdateCommandImplCountry implements UpdateUserCommand {

    private List<String> errors;

    @Autowired
    private UserService userService;

    public UserUpdateCommandImplCountry(List<String> errors, UserService userService) {
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