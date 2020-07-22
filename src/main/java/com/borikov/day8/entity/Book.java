package com.borikov.day8.entity;

import java.util.Collections;
import java.util.List;

public class Book {
    private long bookId;
    private String name;
    private int publishingYear;
    private String publishingHouse;
    private List<String> authors;

    public Book(long bookId, String name, int publishingYear, String publishingHouse, List<String> authors) {
        this.bookId = bookId;
        this.name = name;
        this.publishingYear = publishingYear;
        this.publishingHouse = publishingHouse;
        this.authors = authors;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (bookId != book.bookId) {
            return false;
        }
        if (publishingYear != book.publishingYear) {
            return false;
        }
        if (name == null) {
            if (book.name != null) {
                return false;
            }
        } else {
            if (!name.equals(book.name)) {
                return false;
            }
        }
        if (publishingHouse == null) {
            if (book.publishingHouse != null) {
                return false;
            }
        } else {
            if (!publishingHouse.equals(book.publishingHouse)) {
                return false;
            }
        }
        if (authors == null) {
            if (book.authors != null) {
                return false;
            }
        } else {
            if (!authors.equals(book.authors)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * bookId + publishingYear + ((name != null) ? name.hashCode() : 0)
                + ((publishingHouse != null) ? publishingHouse.hashCode() : 0)
                + ((authors != null) ? authors.hashCode() : 0));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book {");
        sb.append("bookId = ").append(bookId);
        sb.append(", name = '").append(name).append('\'');
        sb.append(", publishingYear = ").append(publishingYear);
        sb.append(", publishingHouse = '").append(publishingHouse).append('\'');
        sb.append(", authors = ").append(authors);
        sb.append('}');
        return sb.toString();
    }
}

