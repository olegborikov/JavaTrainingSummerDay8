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

public class FindBooksByPublishingYearIntervalCommand implements Command {
    @Override
    public Map<String, Object> execute(Map<String, String> data) {
        List<Book> filteredBooks = new ArrayList<>();
        String responseKey = ResponseKeyName.FILTERED_BOOKS;
        if (data != null) {
            try {
                BookServiceImpl bookService = new BookServiceImpl();
                String publishingYearBegin = data.get(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN);
                String publishingYearEnd = data.get(DataKeyName.PUBLISHING_YEAR_INTERVAL_END);
                filteredBooks = bookService.findBooksByPublishingYearInterval(publishingYearBegin, publishingYearEnd);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put(responseKey, filteredBooks);
        return response;
    }
}
