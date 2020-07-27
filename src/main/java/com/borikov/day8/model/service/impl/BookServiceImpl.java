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
    public boolean addBook(String name, String publishingYear, String publishingHouse,
                           List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        boolean result = false;
        int parsedPublishingYear =
                bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isPublishingYearCorrect(parsedPublishingYear)
                && bookValidator.isNameCorrect(name)
                && bookValidator.isPublishingHouseCorrect(publishingHouse)
                && bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                Book book = new Book(null, name, parsedPublishingYear,
                        publishingHouse, authors);
                result = bookDao.add(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean updateBook(String id, String name, String publishingYear,
                              String publishingHouse, List<String> authors)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        boolean result = false;
        long parsedId = bookParser.parseId(id);
        int parsedPublishingYear =
                bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isIdCorrect(parsedId)
                && bookValidator.isPublishingYearCorrect(parsedPublishingYear)
                && bookValidator.isNameCorrect(name)
                && bookValidator.isPublishingHouseCorrect(publishingHouse)
                && bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                Book book = new Book(parsedId, name, parsedPublishingYear,
                        publishingHouse, authors);
                result = bookDao.update(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean removeBook(String id) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        boolean result = false;
        long parsedId = bookParser.parseId(id);
        if (bookValidator.isIdCorrect(parsedId)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                result = bookDao.remove(parsedId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        List<Book> books;
        try {
            BookDaoImpl bookDao = new BookDaoImpl();
            books = bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public Optional<Book> findBookById(String id) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        long parsedId = bookParser.parseId(id);
        Optional<Book> currentBook = Optional.empty();
        if (bookValidator.isIdCorrect(parsedId)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                currentBook = bookDao.findById(parsedId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return currentBook;
    }

    @Override
    public List<Book> findBooksByName(String name) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isRequestCorrect(name)) {
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
    public List<Book> findBooksByPublishingYearInterval(
            String publishingYearBegin, String publishingYearEnd)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> filteredBooks = new ArrayList<>();
        int parsedPublishingYearBegin =
                bookParser.parsePublishingYear(publishingYearBegin);
        int parsedPublishingYearEnd =
                bookParser.parsePublishingYear(publishingYearEnd);
        if (bookValidator.isPublishingYearIntervalCorrect(
                parsedPublishingYearBegin, parsedPublishingYearEnd)) {
            try {
                BookDaoImpl bookDao = new BookDaoImpl();
                filteredBooks = bookDao.findByPublishingYearInterval(
                        parsedPublishingYearBegin, parsedPublishingYearEnd);
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
        if (bookValidator.isRequestCorrect(publishingHouse)) {
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
        if (bookValidator.isRequestCorrect(author)) {
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
