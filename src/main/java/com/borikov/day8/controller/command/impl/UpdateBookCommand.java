package com.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateBookCommand implements Command {
    @Override
    public Map<String, Object> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        boolean result = false;
        String responseKey = ResponseKeyName.UPDATED_BOOK;
        if (data != null) {
            try {
                String id = data.get(DataKeyName.ID);
                String name = data.get(DataKeyName.NAME);
                String publishingYear = data.get(DataKeyName.PUBLISHING_YEAR);
                String publishingHouse = data.get(DataKeyName.PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                int authorNumber = 1;
                while (data.get(DataKeyName.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(DataKeyName.AUTHOR + authorNumber));
                    authorNumber++;
                }
                result = bookService.updateBook(id, name, publishingYear,
                        publishingHouse, authors);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put(responseKey, result);
        return response;
    }
}
