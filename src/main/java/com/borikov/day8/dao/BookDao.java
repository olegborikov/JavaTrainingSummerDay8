package com.borikov.day8.dao;

import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {
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
            System.out.println("Error while resultSet closing");
        }
    }
}
