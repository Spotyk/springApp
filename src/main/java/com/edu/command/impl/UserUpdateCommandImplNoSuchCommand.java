package com.edu.command.impl;

import com.edu.command.UpdateUserCommand;
import com.edu.domain.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserUpdateCommandImplNoSuchCommand implements UpdateUserCommand {

    private List<String> errors;

    @Autowired
    private UserService userService;


    @Override
    public Boolean update(String inputValue, User userBeforeChanges) {
        return false;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

}
