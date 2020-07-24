package com.borikov.day8.main;

import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.dao.impl.BookDaoImpl;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.exception.DaoException;
import com.borikov.day8.model.service.BookService;
import com.borikov.day8.model.service.impl.BookServiceImpl;
import com.borikov.day8.pool.ConnectionPool;
import com.borikov.day8.util.BookParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final String FIND_BOOK_BY_ID = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE id = ?";

    public static void main(String[] args) {
        long id = 11;
        Optional<Book> currentBook = Optional.empty();
        ResultSet resultSet = null;
        Book book = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = createBookFromResultSet(resultSet);
                //books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        } finally {
            closeResultSet(resultSet);
        }
        System.out.println(book);
    }
    private static Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
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

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while resultSet closing");
        }
    }
}
