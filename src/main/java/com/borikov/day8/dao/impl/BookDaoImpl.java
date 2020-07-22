package com.borikov.day8.dao.impl;

import com.borikov.day8.dao.BookDao;
import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.pool.DataSource;
import com.borikov.day8.util.BookCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void add(Book book) throws DaoException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, book.getAuthors().stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", ")));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setBookId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Adding book error", e);
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_BOOK)) {
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, book.getAuthors().stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(", ")));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Removing book error", e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS)) {
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
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_NAME)) {
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
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_YEAR)) {
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
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_HOUSE)) {
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
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_AUTHOR)) {
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
