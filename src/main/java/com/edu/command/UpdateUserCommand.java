package com.edu.command;

import com.edu.domain.entity.User;

import java.util.List;

public interface UpdateUserCommand {
    Boolean update(String inputValue, User userBeforeChanges);

    List<String> getErrors();

}
