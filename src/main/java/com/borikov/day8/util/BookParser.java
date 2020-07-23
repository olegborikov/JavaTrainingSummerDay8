package com.borikov.day8.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookParser {
    private static final String COMMA = ", ";

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
