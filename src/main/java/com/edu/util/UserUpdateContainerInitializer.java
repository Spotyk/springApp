package com.edu.util;

import com.edu.command.UpdateCommandContainer;
import com.edu.command.UpdateUserCommand;
import com.edu.command.impl.UserUpdateCommandImplBirthDate;
import com.edu.command.impl.UserUpdateCommandImplCountry;
import com.edu.command.impl.UserUpdateCommandImplEmail;
import com.edu.command.impl.UserUpdateCommandImplPassword;
import com.edu.command.impl.UserUpdateCommandImplState;
import com.edu.command.impl.UserUpdateCommandImplUsername;
import com.edu.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUpdateContainerInitializer {
    private UserService userService;

    public UserUpdateContainerInitializer(UserService userService) {
        this.userService = userService;
    }

    public UpdateCommandContainer initCommands() {
        Map<String, UpdateUserCommand> commands = new HashMap<>();
        List<String> errors = new ArrayList<>();

        commands.put("username", new UserUpdateCommandImplUsername(errors, userService));
        commands.put("password", new UserUpdateCommandImplPassword(errors, userService));
        commands.put("state", new UserUpdateCommandImplState(errors, userService));
        commands.put("country", new UserUpdateCommandImplCountry(errors, userService));
        commands.put("birthDate", new UserUpdateCommandImplBirthDate(errors, userService));
        commands.put("email", new UserUpdateCommandImplEmail(errors, userService));

        return new UpdateCommandContainer(commands);
    }
}
