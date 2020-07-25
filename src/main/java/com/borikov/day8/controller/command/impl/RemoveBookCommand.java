package com.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.model.entity.Book;

import java.util.List;
import java.util.Map;

public class RemoveBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
       /* BookServiceImpl bookService = new BookServiceImpl();
        List<Book> removedBook = new ArrayList<>();
        String responseKey = ResponseKeyName.REMOVED_BOOK;
        if (data != null) {
            try {
                String name = data.get(DataKeyName.NAME);
                String publishingYear = data.get(DataKeyName.PUBLISHING_YEAR);
                String publishingHouse = data.get(DataKeyName.PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                int authorNumber = 1;
                while (data.get(DataKeyName.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(DataKeyName.AUTHOR + authorNumber));
                    authorNumber++;
                }
                removedBook = bookService.removeBook(name, publishingYear,
                        publishingHouse, authors);
            } catch (ServiceException e) {
                responseKey = ResponseKeyName.ERROR;
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(responseKey, removedBook);*/
        return null;
    }
}
