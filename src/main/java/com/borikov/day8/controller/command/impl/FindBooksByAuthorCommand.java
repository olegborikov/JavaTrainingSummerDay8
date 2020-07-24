package com.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.impl.constant.DataKeyName;
import com.borikov.day8.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByAuthorCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        List<Book> filteredBooks = new ArrayList<>();
        String responseKey = ResponseKeyName.FILTERED_BOOKS;
        if (data != null) {
            try {
                BookServiceImpl bookService = new BookServiceImpl();
                String author = data.get(DataKeyName.AUTHOR);
                filteredBooks = bookService.findBooksByAuthor(author);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(responseKey, filteredBooks);
        return response;
    }
} 
