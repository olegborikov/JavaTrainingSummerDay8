package com.borikov.day8.validator;

import java.util.Calendar;
import java.util.List;

public class BookValidator {
    private static final int MIN_PUBLISHING_YEAR = 1;
    private static final int MAX_PUBLISHING_YEAR =
            Calendar.getInstance().get(Calendar.YEAR);
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 100_000;
    private static final int MAX_STRING_LENGTH = 40;
    private static final int MIN_STRING_LENGTH = 1;
    private static final int MAX_AUTHORS_AMOUNT = 10;

    public boolean isIdCorrect(long id) {
        boolean result = false;
        if (id >= MIN_ID && id <= MAX_ID) {
            result = true;
        }
        return result;
    }

    public boolean isNameCorrect(String name) {
        boolean result = false;
        if (name != null && !name.isBlank() &&
                name.length() >= MIN_STRING_LENGTH &&
                name.length() <= MAX_STRING_LENGTH) {
            result = true;
        }
        return result;
    }

    public boolean isPublishingYearCorrect(int publishingYear) {
        boolean result = false;
        if (publishingYear >= MIN_PUBLISHING_YEAR &&
                publishingYear <= MAX_PUBLISHING_YEAR) {
            result = true;
        }
        return result;
    }

    public boolean isPublishingHouseCorrect(String publishingHouse) {
        boolean result = false;
        if (publishingHouse != null && !publishingHouse.isBlank() &&
                publishingHouse.length() >= MIN_STRING_LENGTH &&
                publishingHouse.length() <= MAX_STRING_LENGTH) {
            result = true;
        }
        return result;
    }

    public boolean isAuthorsCorrect(List<String> authors) {
        boolean result = false;
        if (authors != null &&
                authors.size() <= MAX_AUTHORS_AMOUNT &&
                authors.stream().allMatch(author -> isAuthorCorrect(author))) {
            result = true;
        }
        return result;
    }

    public boolean isAuthorCorrect(String author) {
        boolean result = false;
        if (author != null && !author.isBlank() &&
                author.length() >= MIN_STRING_LENGTH &&
                author.length() <= MAX_STRING_LENGTH) {
            result = true;
        }
        return result;
    }
}

