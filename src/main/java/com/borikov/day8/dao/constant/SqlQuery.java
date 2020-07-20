package com.borikov.day8.dao.constant;

public class SqlQuery {
    public static final String FIND_ALL_BOOKS = "SELECT * FROM books";
    public static final String FIND_BOOK_BY_NAME = "SELECT * FROM books WHERE books.name = ?";

    private SqlQuery() {
    }
}
