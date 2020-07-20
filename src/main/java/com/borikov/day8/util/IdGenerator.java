package com.borikov.day8.util;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 100_000;
    private static long counter = 0;

    private IdGenerator() {
    }

    public static long generateId() {
        if (MIN_ID + counter > MAX_ID) {
            counter = 0;
        }
        long id = MIN_ID + counter;
        counter++;
        return id;
    }
}
