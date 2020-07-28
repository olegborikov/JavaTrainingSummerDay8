package com.borikov.day8.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookParser {
    private static final String COMMA = ", ";
    private static final int DEFAULT_VALUE_NUMBER = -1;
    private static final String DEFAULT_VALUE_STRING = "";

    public long parseId(String id) {
        long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            parsedId = DEFAULT_VALUE_NUMBER;
        }
        return parsedId;
    }

    public int parsePublishingYear(String publishingYear) {
        int parsedPublishingYear;
        try {
            parsedPublishingYear = Integer.parseInt(publishingYear);
        } catch (NumberFormatException e) {
            parsedPublishingYear = DEFAULT_VALUE_NUMBER;
        }
        return parsedPublishingYear;
    }

    public List<String> parseAuthorsToList(String authors) {
        List<String> parsedAuthors = new ArrayList<>();
        if (authors != null && !authors.isBlank()) {
            String[] authorsArr = authors.split(COMMA);
            parsedAuthors = new ArrayList<>(Arrays.asList(authorsArr));
        }
        return parsedAuthors;
    }

    public String parseAuthorsToString(List<String> authors) {
        String parsedAuthors = DEFAULT_VALUE_STRING;
        if (authors != null) {
            parsedAuthors = authors.stream()
                    .map(a -> String.valueOf(a))
                    .collect(Collectors.joining(COMMA));
        }
        return parsedAuthors;
    }
}
