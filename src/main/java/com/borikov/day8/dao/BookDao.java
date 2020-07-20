package com.borikov.day8.dao;

import com.borikov.day8.entity.Book;
import com.borikov.day8.exception.DaoException;

import java.util.List;

public interface BookDao {
    List<Book> findAll() throws DaoException;

    List<Book> findByName(String name) throws DaoException;
}
