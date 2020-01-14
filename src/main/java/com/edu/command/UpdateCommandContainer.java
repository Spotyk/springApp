package com.edu.command;

import java.util.Map;

public class UpdateCommandContainer {
    private Map<String, UpdateUserCommand> commandMap;

    public UpdateCommandContainer(Map<String, UpdateUserCommand> commandMap) {
        this.commandMap = commandMap;
    }

    public UpdateUserCommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
