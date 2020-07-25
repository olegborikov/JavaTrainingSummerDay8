package com.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBookByIdCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        List<Book> currentBook = new ArrayList<>();
        String responseKey = ResponseKeyName.CURRENT_BOOK;
        if (data != null) {
            try {
            BookServiceImpl bookService = new BookServiceImpl();
            String id = data.get(DataKeyName.ID);
                currentBook = bookService.findBookById(id);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(responseKey, currentBook);
        return response;
    }
}
