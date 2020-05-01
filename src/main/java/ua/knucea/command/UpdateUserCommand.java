package ua.knucea.command;

import ua.knucea.domain.entity.User;

import java.util.List;

public interface UpdateUserCommand {
    Boolean update(String inputValue, User userBeforeChanges);

    List<String> getErrors();

}
