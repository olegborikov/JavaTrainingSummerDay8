package com.borikov.day8.dao.constant;

public class SqlQuery {
    public static final String FIND_ALL_BOOKS = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books";
    public static final String FIND_BOOK_BY_NAME = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE name LIKE ?";

    private SqlQuery() {
    }
}
