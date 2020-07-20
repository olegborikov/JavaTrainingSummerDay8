package com.borikov.day8.dao.impl;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.dao.constant.SqlQuery;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int publishingYear = resultSet.getInt("publishingYear");
                String publishingHouse = resultSet.getString("publishingHouse");
                List<String> authors = new ArrayList<>(Arrays.asList(resultSet.getString("authors").split(", ")));
                Book book = new Book(id, name, publishingYear, publishingHouse, authors);
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
        try (PreparedStatement statement = DataSource.getConnection().prepareStatement(SqlQuery.FIND_BOOK_BY_NAME);
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_BOOKS);
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String bookName = resultSet.getString("name");
                int publishingYear = resultSet.getInt("publishingYear");
                String publishingHouse = resultSet.getString("publishingHouse");
                List<String> authors = new ArrayList<>(Arrays.asList(resultSet.getString("authors").split(", ")));
                Book book = new Book(id, bookName, publishingYear, publishingHouse, authors);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by name error", e);
        }
        return books;
    }
}
