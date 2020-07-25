package com.borikov.day8.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookParser {
    private static final String COMMA = ", ";
    private static final int DEFAULT_VALUE = -1;

    public long parseId(String id) {
        long idParsed;
        try {
            idParsed = Long.parseLong(id);
        } catch (NumberFormatException e) {
            idParsed = DEFAULT_VALUE;
        }
        return idParsed;
    }

    public int parsePublishingYear(String publishingYear) {
        int publishingYearParsed;
        try {
            publishingYearParsed = Integer.parseInt(publishingYear);
        } catch (NumberFormatException e) {
            publishingYearParsed = DEFAULT_VALUE;
        }
        return publishingYearParsed;
    }

    public List<String> parseStringToList(String authors) {
        String[] authorsArr = authors.split(COMMA);
        List<String> authorsParsed = new ArrayList<>(Arrays.asList(authorsArr));
        return authorsParsed;
    }

    public String parseListToString(List<String> authors) {
        String authorsParsed = authors.stream()
                .map(a -> String.valueOf(a))
                .collect(Collectors.joining(COMMA));
        return authorsParsed;
    }
}
