package com.borikov.day8.controller;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.CommandProvider;
import com.borikov.day8.model.entity.Book;

import java.util.List;
import java.util.Map;

public class BookController {
    public Map<String, List<Book>> processRequest(String request,
                                                  Map<String, String> data) {
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.defineCommand(request);
        Map<String, List<Book>> response = command.execute(data);
        return response;
    }
}
