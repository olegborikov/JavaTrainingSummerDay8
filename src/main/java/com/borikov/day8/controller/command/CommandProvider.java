package com.borikov.day8.controller.command;

public class CommandProvider {
    private static final CommandType DEFAULT_TYPE = CommandType.FIND_ALL_BOOKS;

    public Command defineCommand(String request) {
        CommandType currentType;
        if (request == null) {
            currentType = DEFAULT_TYPE;
        } else {
            try {
                currentType = CommandType.valueOf(request.toUpperCase());
            } catch (IllegalArgumentException e) {
                currentType = DEFAULT_TYPE;
            }
        }
        Command currentCommand = currentType.getCommand();
        return currentCommand;
    }
}
