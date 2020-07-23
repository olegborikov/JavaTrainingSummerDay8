package com.borikov.day8.dao.impl;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.dao.SqlQuery;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.pool.BookDataSource;
import com.borikov.day8.pool.ConnectionPool;
import com.borikov.day8.util.BookCreator;
import com.borikov.day8.util.BookParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean add(Book book) throws DaoException {
        boolean result;
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            BookParser bookParser = new BookParser();
            String authorsParsed = bookParser.parseListToString(book.getAuthors());
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, authorsParsed);
            int rows = statement.executeUpdate();
            result = rows > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setBookId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Adding book error", e);
        }
        return result;
    }

    @Override
    public boolean remove(Book book) throws DaoException {
        boolean result;
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_BOOK)) {
            BookParser bookParser = new BookParser();
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, bookParser.parseListToString(book.getAuthors()));
            int rows = statement.executeUpdate();
            result = rows > 0;
        } catch (SQLException e) {
            throw new DaoException("Removing book error", e);
        }
        return result;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
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
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_NAME)) {
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
            closeResultSet(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findByPublishingYear(int publishingYear) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_PUBLISHING_YEAR)) {
            statement.setInt(1, publishingYear);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by publishing year error", e);
        } finally {
            closeResultSet(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_PUBLISHING_HOUSE)) {
            statement.setString(1, publishingHouse);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by publishing house error", e);
        } finally {
            closeResultSet(resultSet);
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = BookDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_BY_AUTHOR)) {
            statement.setString(1, "%" + author + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookCreator bookCreator = new BookCreator();
                Book book = bookCreator.createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by author error", e);
        } finally {
            closeResultSet(resultSet);
        }
        return books;
    }
}
