package com.borikov.day8.model.dao;

public class SqlQuery {
    public static final String ADD_BOOK = "INSERT INTO books (name, publishingYear, " +
            "publishingHouse, authors) VALUES (?, ?, ?, ?) ";
    public static final String REMOVE_BOOK = "DELETE FROM books WHERE name LIKE ? " +
            "AND publishingYear = ? AND publishingHouse LIKE ?" +
            "AND authors LIKE ?";
    public static final String FIND_ALL_BOOKS = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books";
    public static final String FIND_BOOK_BY_NAME = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE name LIKE ?";
    public static final String FIND_BOOK_BY_PUBLISHING_YEAR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingYear = ?";
    public static final String FIND_BOOK_BY_PUBLISHING_HOUSE = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE publishingHouse LIKE ?";
    public static final String FIND_BOOK_BY_AUTHOR = "SELECT id, name, publishingYear, " +
            "publishingHouse, authors FROM books WHERE authors LIKE ?";

    private SqlQuery() {
    }
}
