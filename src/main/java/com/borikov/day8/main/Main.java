package com.borikov.day8.main;

import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.dao.impl.BookDaoImpl;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.model.service.BookService;
import com.borikov.day8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            BookService bookService = new BookServiceImpl();
            boolean result = bookService.addBook("Qwerty", "2021", "Минск", new ArrayList<>());
            System.out.println(result);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
