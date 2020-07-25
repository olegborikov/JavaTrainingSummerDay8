package com.borikov.day8.controller.command;

import com.borikov.day8.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface Command {
    Map<String, List<Book>> execute(Map<String, String> data);// TODO: 24.07.2020 String, Object
}
