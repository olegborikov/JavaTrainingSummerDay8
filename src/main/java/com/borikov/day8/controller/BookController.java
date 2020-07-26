package com.borikov.day8.controller;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.CommandProvider;

import java.util.Map;

public class BookController {
    public Map<String, Object> processRequest(
            String request, Map<String, String> data) {
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.defineCommand(request);
        Map<String, Object> response = command.execute(data);
        return response;
    }
}
