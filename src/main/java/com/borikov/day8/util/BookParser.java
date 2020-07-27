package com.borikov.day8.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookParser {
    private static final String COMMA = ", ";
    private static final int DEFAULT_VALUE = -1;

    public long parseId(String id) {
        long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            parsedId = DEFAULT_VALUE;
        }
        return parsedId;
    }

    public int parsePublishingYear(String publishingYear) {
        int parsedPublishingYear;
        try {
            parsedPublishingYear = Integer.parseInt(publishingYear);
        } catch (NumberFormatException e) {
            parsedPublishingYear = DEFAULT_VALUE;
        }
        return parsedPublishingYear;
    }

    public List<String> parseAuthorsToList(String authors) {
        List<String> parsedAuthors = new ArrayList<>();
        if (!authors.isBlank()) {
            String[] authorsArr = authors.split(COMMA);
            parsedAuthors = new ArrayList<>(Arrays.asList(authorsArr));
        }
        return parsedAuthors;
    }

    public String parseAuthorsToString(List<String> authors) {
        String parsedAuthors = authors.stream()
                .map(a -> String.valueOf(a))
                .collect(Collectors.joining(COMMA));
        return parsedAuthors;
    }
}
