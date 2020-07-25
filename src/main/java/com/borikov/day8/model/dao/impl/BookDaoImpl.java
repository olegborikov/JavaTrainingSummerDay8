package com.borikov.day8.model.dao.impl;

import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.dao.ColumnName;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.model.dao.pool.ConnectionPool;
import com.borikov.day8.util.BookParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private static final String ADD_BOOK = "INSERT INTO books " +
            "(name, publishing_year, publishing_house, authors) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_BOOK_BY_ALL_PARAMETERS = "DELETE FROM books " +
            "WHERE name LIKE ? AND publishing_year = ? " +
            "AND publishing_house LIKE ? AND authors LIKE ?";
    private static final String UPDATE_BOOK_BY_ID = "UPDATE books " +
            "SET name = ?, publishing_year = ?, publishing_house = ?, authors = ? " +
            "WHERE book_id = ?";
    private static final String FIND_ALL_BOOKS = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books";
    private static final String FIND_BOOK_BY_ID = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books WHERE book_id = ?";
    private static final String FIND_BOOK_BY_NAME = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books " +
            "WHERE name LIKE ? ORDER BY name";
    private static final String FIND_BOOK_BY_PUBLISHING_YEAR = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books " +
            "WHERE CAST(publishing_year AS CHAR(20)) LIKE ? ORDER BY publishing_year";
    private static final String FIND_BOOK_BY_PUBLISHING_HOUSE = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books " +
            "WHERE publishing_house LIKE ? ORDER BY publishing_house";
    private static final String FIND_BOOK_BY_AUTHOR = "SELECT book_id, name, " +
            "publishing_year, publishing_house, authors FROM books " +
            "WHERE authors LIKE ? ORDER BY CHAR_LENGTH(authors)";
    private static final String PERCENT = "%";

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
                     connection.prepareStatement(REMOVE_BOOK_BY_ALL_PARAMETERS)) {
            BookParser bookParser = new BookParser();
            String authorsParsed = bookParser.parseListToString(book.getAuthors());
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, authorsParsed);
            int rows = statement.executeUpdate();
            result = rows > 0;
        } catch (SQLException e) {
            throw new DaoException("Removing book error", e);
        }
        return result;
    }

    @Override
    public boolean update(Book book) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_BOOK_BY_ID)) {
            BookParser bookParser = new BookParser();
            String authorsParsed = bookParser.parseListToString(book.getAuthors());
            statement.setString(1, book.getName());
            statement.setInt(2, book.getPublishingYear());
            statement.setString(3, book.getPublishingHouse());
            statement.setString(4, authorsParsed);
            statement.setLong(5, book.getBookId());
            int rows = statement.executeUpdate();
            result = rows > 0;
        } catch (SQLException e) {
            throw new DaoException("Updating book error", e);
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
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BOOK_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = createBookFromResultSet(resultSet);
                currentBook = Optional.of(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding books by id error", e);
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
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BOOK_BY_NAME)) {
            statement.setString(1, PERCENT + name + PERCENT);
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
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_YEAR)) {
            statement.setString(1, PERCENT + publishingYear + PERCENT);
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
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BOOK_BY_PUBLISHING_HOUSE)) {
            statement.setString(1, PERCENT + publishingHouse + PERCENT);
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
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BOOK_BY_AUTHOR)) {
            statement.setString(1, PERCENT + author + PERCENT);
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
        long id = resultSet.getInt(ColumnName.BOOK_ID);
        String name = resultSet.getString(ColumnName.NAME);
        int publishingYear = resultSet.getInt(ColumnName.PUBLISHING_YEAR);
        String publishingHouse = resultSet.getString(ColumnName.PUBLISHING_HOUSE);
        String authors = resultSet.getString(ColumnName.AUTHORS);
        BookParser bookParser = new BookParser();
        List<String> authorsParsed = bookParser.parseStringToList(authors);
        Book book = new Book(id, name, publishingYear, publishingHouse, authorsParsed);
        return book;
    }
}
