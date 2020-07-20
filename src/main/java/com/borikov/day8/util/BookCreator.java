package com.borikov.day8.util;

import com.borikov.day8.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookCreator {
    public Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
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
