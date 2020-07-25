package com.borikov.day8.model.dao;

import com.borikov.day8.model.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    boolean add(Book book) throws DaoException;

    boolean remove(Book book) throws DaoException;

    Book update(Book book) throws DaoException;

    List<Book> findAll() throws DaoException;

    Optional<Book> findById(long id) throws DaoException;

    List<Book> findByName(String name) throws DaoException;

    List<Book> findByPublishingYear(int publishingYear) throws DaoException;

    List<Book> findByPublishingHouse(String publishingHouse) throws DaoException;

    List<Book> findByAuthor(String author) throws DaoException;

    List<Book> sortByName() throws DaoException;

    List<Book> sortByPublishingYear() throws DaoException;

    List<Book> sortByPublishingHouse() throws DaoException;

    List<Book> sortByAuthors() throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println("Error while resultSet closing");
        }
    }
}
