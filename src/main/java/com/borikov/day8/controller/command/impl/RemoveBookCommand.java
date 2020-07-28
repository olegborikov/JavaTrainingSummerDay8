package com.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements Command {
    @Override
    public Map<String, Object> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        boolean result = false;
        String responseKey = ResponseKeyName.REMOVED_BOOK;
        if (data != null) {
            try {
                String id = data.get(DataKeyName.ID);
                result = bookService.removeBook(id);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put(responseKey, result);
        return response;
    }
}
