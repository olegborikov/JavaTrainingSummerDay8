package com.borikov.day8.dao;

import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    String ADD_BOOK = "INSERT INTO books (name, publishingYear, " +
            "publishingHouse, authors) VALUES (?, ?, ?, ?) ";
    String REMOVE_BOOK = "DELETE FROM books WHERE name LIKE ? " +
            "AND publishingYear = ? AND publishingHouse LIKE ?" +
            "AND authors LIKE ?";
    String FIND_ALL_BOOKS = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books";
    String FIND_BOOK_BY_NAME = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE name LIKE ?";
    String FIND_BOOK_BY_PUBLISHING_YEAR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingYear = ?";
    String FIND_BOOK_BY_PUBLISHING_HOUSE = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingHouse LIKE ?";
    String FIND_BOOK_BY_AUTHOR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE authors LIKE ?";

    void add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    List<Book> findAll() throws DaoException;

    List<Book> findByName(String name) throws DaoException;

    List<Book> findByPublishingYear(int publishingYear) throws DaoException;

    List<Book> findByPublishingHouse(String publishingHouse) throws DaoException;

    List<Book> findByAuthor(String author) throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("");
        }
    }
}
