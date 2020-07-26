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

    public boolean isIdCorrect(Long id) {
        boolean result = false;
        if (id != null) {
            result = id >= MIN_ID && id <= MAX_ID;
        }
        return result;
    }

    public boolean isNameCorrect(String name) {
        boolean result = false;
        if (name != null && !name.isBlank()) {
            result = name.length() >= MIN_STRING_LENGTH &&
                    name.length() <= MAX_STRING_LENGTH;
        }
        return result;
    }

    public boolean isPublishingYearCorrect(int publishingYear) {
        boolean result = publishingYear >= MIN_PUBLISHING_YEAR &&
                publishingYear <= MAX_PUBLISHING_YEAR;
        return result;
    }

    public boolean isPublishingYearIntervalCorrect(
            int publishingYearBegin, int publishingYearEnd) {
        boolean result = false;
        if (publishingYearBegin <= publishingYearEnd) {
            result = isPublishingYearCorrect(publishingYearBegin) &&
                    isPublishingYearCorrect(publishingYearEnd);
        }
        return result;
    }

    public boolean isPublishingHouseCorrect(String publishingHouse) {
        boolean result = false;
        if (publishingHouse != null && !publishingHouse.isBlank()) {
            result = publishingHouse.length() >= MIN_STRING_LENGTH &&
                    publishingHouse.length() <= MAX_STRING_LENGTH;
        }
        return result;
    }

    public boolean isAuthorsCorrect(List<String> authors) {
        boolean result = false;
        if (authors != null) {
            result = authors.size() <= MAX_AUTHORS_AMOUNT &&
                    authors.stream().allMatch(author -> isAuthorCorrect(author));
        }
        return result;
    }

    public boolean isAuthorCorrect(String author) {
        boolean result = false;
        if (author != null && !author.isBlank()) {
            result = author.length() >= MIN_STRING_LENGTH &&
                    author.length() <= MAX_STRING_LENGTH;
        }
        return result;
    }

    public boolean isRequestCorrect(String request) {
        boolean result = false;
        if (request != null && !(request.isBlank() && !request.isEmpty())) {
            result = request.length() <= MAX_STRING_LENGTH;
        }
        return result;
    }
}

