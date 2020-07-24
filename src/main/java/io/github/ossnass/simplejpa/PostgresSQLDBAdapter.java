package io.github.ossnass.simplejpa;


public class PostgresSQLDBAdapter
        extends DBAdapter {
    public String createURL(String host, int port, String database) {
        if (host == null)
            throw new IllegalArgumentException("The Host of database cannot be null");
        if (host.trim().equals(""))
            throw new IllegalArgumentException("The Host of database cannot be empty srting");
        if (database == null)
            throw new IllegalArgumentException("The name of database cannot be null");
        if (database.trim().equals(""))
            throw new IllegalArgumentException("The name of database cannot be empty srting");
        if (port == 0)
            throw new IllegalArgumentException("The port of database cannot be zero");
        return String.format("jdbc:postgresql://%s:%d/%s", host, Integer.valueOf(port), database);
    }


    public String getAdapterName() {
        return "PostgreSQL";
    }


    public String[] availableExtraProperties() {
        return null;
    }
}