package io.github.ossnass.simplejpa;


public class JPAAdapter {
    private static JPAAdapter eclipseLink;
    private String URL;
    private String Driver;
    private String Username;
    private String Password;

    public JPAAdapter(String URL, String driver, String username, String password) {
        if (URL == null || URL.equals(""))
            throw new IllegalArgumentException("Database URL property name cannot be null or empty");
        if (driver == null || driver.equals(""))
            throw new IllegalArgumentException("Database driver property name cannot be null or empty");
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException("Database username property name cannot be null or empty");
        }
        if (password == null || password.equals(""))
            throw new IllegalArgumentException("Database password property name cannot be null or empty");
        this.URL = URL;
        this.Driver = driver;
        this.Username = username;
        this.Password = password;
    }

    public static JPAAdapter getEclipseLinkAdapter() {
        if (eclipseLink == null) {
            eclipseLink = new JPAAdapter("javax.persistence.jdbc.url", "javax.persistence.jdbc.driver", "javax.persistence.jdbc.user", "javax.persistence.jdbc.password");
        }
        return eclipseLink;
    }

    public static JPAAdapter getOPenJPAAdapter() {
        if (eclipseLink == null) {
            eclipseLink = new JPAAdapter("javax.persistence.jdbc.url", "javax.persistence.jdbc.driver", "javax.persistence.jdbc.user", "javax.persistence.jdbc.password");
        }
        return eclipseLink;
    }

    public String getJPAURL() {
        return this.URL;
    }

    public String getDriver() {
        return this.Driver;
    }

    public void setDriver(String driver) {
        if (driver == null || driver.equals(""))
            throw new IllegalArgumentException("Database driver property name cannot be null or empty");
        this.Driver = driver;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        if (username == null || username.equals(""))
            throw new IllegalArgumentException("Database username property name cannot be null or empty");
        this.Username = username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        if (password == null || password.equals(""))
            throw new IllegalArgumentException("Database password property name cannot be null or empty");
        this.Password = password;
    }

    public void setURL(String URL) {
        if (URL == null || URL.equals(""))
            throw new IllegalArgumentException("Database URL property name cannot be null or empty");
        this.URL = URL;
    }
}