package com.borikov.day8.model.dao.impl;

import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.pool.ConnectionPool;
import com.borikov.day8.util.BookParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private static final String ADD_BOOK = "INSERT INTO books (name, publishingYear, " +
            "publishingHouse, authors) VALUES (?, ?, ?, ?) ";
    private static final String REMOVE_BOOK = "DELETE FROM books WHERE name LIKE ? " +
            "AND publishingYear = ? AND publishingHouse LIKE ?" +
            "AND authors LIKE ?";
    private static final String FIND_ALL_BOOKS = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books";
    private static final String FIND_BOOK_BY_ID = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE id = ?";
    private static final String FIND_BOOK_BY_NAME = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE name LIKE ?";
    private static final String FIND_BOOK_BY_PUBLISHING_YEAR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingYear = ?";
    private static final String FIND_BOOK_BY_PUBLISHING_HOUSE = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingHouse LIKE ?";
    private static final String FIND_BOOK_BY_AUTHOR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE authors LIKE ?";

    @Override
    public boolean add(Book book) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(ADD_BOOK,
                             Statement.RETURN_GENERATED_KEYS)) {
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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(REMOVE_BOOK)) {
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
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS)) {
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding all books error", e);
        }
        return books;
    }

    @Override
    public Optional<Book> findById(long id) throws DaoException {
        Optional<Book> currentBook = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                currentBook = Optional.of(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by name error", e);
        } finally {
            closeResultSet(resultSet);
        }
        return currentBook;
    }

    @Override
    public List<Book> findByName(String name) throws DaoException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_YEAR)) {
            statement.setInt(1, publishingYear);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_HOUSE)) {
            statement.setString(1, publishingHouse);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
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
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_AUTHOR)) {
            statement.setString(1, "%" + author + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by author error", e);
        } finally {
            closeResultSet(resultSet);
        }
        return books;
    }

    private Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int publishingYear = resultSet.getInt("publishingYear");
        String publishingHouse = resultSet.getString("publishingHouse");
        String authors = resultSet.getString("authors");
        BookParser bookParser = new BookParser();
        List<String> authorsParsed = bookParser.parseStringToList(authors);
        Book book = new Book(id, name, publishingYear, publishingHouse, authorsParsed);
        return book;
    }
}
