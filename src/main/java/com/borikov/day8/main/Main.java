package com.borikov.day8.main;

import com.borikov.day8.exception.DaoException;
import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.dao.impl.BookDaoImpl;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.model.dao.pool.ConnectionPool;
import com.borikov.day8.util.BookParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = new ArrayList<>();

        try{
            books = bookDao.findByPublishingYear(1);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
