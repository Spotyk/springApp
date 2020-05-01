package ua.knucea.util;

import ua.knucea.command.UpdateCommandContainer;
import ua.knucea.command.UpdateUserCommand;
import ua.knucea.command.impl.UserUpdateCommandImplBirthDate;
import ua.knucea.command.impl.UserUpdateCommandImplCountry;
import ua.knucea.command.impl.UserUpdateCommandImplEmail;
import ua.knucea.command.impl.UserUpdateCommandImplPassword;
import ua.knucea.command.impl.UserUpdateCommandImplState;
import ua.knucea.command.impl.UserUpdateCommandImplUsername;
import ua.knucea.service.UserService;

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
