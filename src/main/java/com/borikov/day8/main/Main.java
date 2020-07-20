package com.borikov.day8.main;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.dao.impl.BookDaoImpl;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BookDao bookDao = BookDaoImpl.getInstance();
            List<Book> books = bookDao.findAll();
            System.out.println(books);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
