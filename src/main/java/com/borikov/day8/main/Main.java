package com.borikov.day8.main;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.dao.impl.BookDaoImpl;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BookDao bookDao = new BookDaoImpl();
            Book book1 = new Book(25, "1", 1, "1", Arrays.asList("1", "2"));
            bookDao.add(book1);
            System.out.println(book1);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
