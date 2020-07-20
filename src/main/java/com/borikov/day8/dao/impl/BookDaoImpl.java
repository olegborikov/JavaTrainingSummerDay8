package com.borikov.day8.dao.impl;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.dao.constant.SqlQuery;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.pool.DataSource;
import com.borikov.day8.util.BookCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static BookDao INSTANCE;

    private BookDaoImpl() {
    }

    public static BookDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_BOOKS)) {
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding all books error", e);
        }
        return books;
    }

    @Override
    public List<Book> findByName(String name) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_NAME);) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by name error", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e); // TODO: 20.07.2020 what to do? 
            }
        }
        return books;
    }
}
