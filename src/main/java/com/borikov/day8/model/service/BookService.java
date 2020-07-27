package com.borikov.day8.model.service;

import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    boolean addBook(String name, String publishingYear,
                    String publishingHouse, List<String> authors)
            throws ServiceException;

    boolean updateBook(String id, String name, String publishingYear,
                       String publishingHouse, List<String> authors)
            throws ServiceException;

    boolean removeBook(String id) throws ServiceException;

    List<Book> findAllBooks() throws ServiceException;

    Optional<Book> findBookById(String id) throws ServiceException;

    List<Book> findBooksByName(String name) throws ServiceException;

    List<Book> findBooksByPublishingYearInterval(
            String publishingYearBegin, String publishingYearEnd)
            throws ServiceException;

    List<Book> findBooksByPublishingHouse(String publishingHouse)
            throws ServiceException;

    List<Book> findBooksByAuthor(String author) throws ServiceException;
}
