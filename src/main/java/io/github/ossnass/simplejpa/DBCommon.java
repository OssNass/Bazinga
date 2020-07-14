package io.github.ossnass.simplejpa;


public class DBCommon {
    private static Exception lastException;

    public static Exception getLastException() {
        return lastException;
    }


    public static void setLastException(Exception lastException) {
        DBCommon.lastException = lastException;
    }
}