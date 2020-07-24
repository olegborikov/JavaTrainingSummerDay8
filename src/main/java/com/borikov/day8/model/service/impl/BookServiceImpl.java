package com.borikov.day8.model.service.impl;

import com.borikov.day8.exception.DaoException;
import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.dao.impl.BookDaoImpl;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.model.service.BookService;
import com.borikov.day8.util.BookParser;
import com.borikov.day8.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public boolean addBook(String name, String publishingYear,
                           String publishingHouse,
                           List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        boolean result = false;
        int publishingYearParsed =
                bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isPublishingYearCorrect(publishingYearParsed) &&
                bookValidator.isNameCorrect(name) &&
                bookValidator.isPublishingHouseCorrect(publishingHouse) &&
                bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                Book book = new Book(null, name, publishingYearParsed,
                        publishingHouse, authors);
                bookDao.add(book);
                result = true;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean removeBook(String name, String publishingYear,
                              String publishingHouse,
                              List<String> authors)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        boolean result = false;
        int publishingYearParsed =
                bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isPublishingYearCorrect(publishingYearParsed) &&
                bookValidator.isNameCorrect(name) &&
                bookValidator.isPublishingHouseCorrect(publishingHouse) &&
                bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                Book book = new Book(null, name, publishingYearParsed,
                        publishingHouse, authors);
                bookDao.remove(book);
                result = true;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        try {
            BookDaoImpl bookDao = new BookDaoImpl();
            List<Book> books = bookDao.findAll();
            return books;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> findBookById(String id) throws ServiceException {
        return null;
    }

    @Override
    public List<Book> findBooksByName(String name) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isNameCorrect(name)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                filteredBooks = bookDao.findByName(name);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingYear(String publishingYear)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> filteredBooks = new ArrayList<>();
        int publishingYearParsed =
                bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isPublishingYearCorrect(publishingYearParsed)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                filteredBooks =
                        bookDao.findByPublishingYear(publishingYearParsed);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                filteredBooks = bookDao.findByPublishingHouse(publishingHouse);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isAuthorCorrect(author)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                filteredBooks = bookDao.findByAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return filteredBooks;
    }
}
