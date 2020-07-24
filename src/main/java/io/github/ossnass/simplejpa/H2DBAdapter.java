package io.github.ossnass.simplejpa;


public class H2DBAdapter
        extends DBAdapter {
    public H2DBAdapter() {
        this.changeUserPassword = "alter user %s set password '%s';";
        this.acquireUserRoles = "select * from INFORMATION_SCHEMA.RIGHTS where GRANTEE = current_user;";
        this.driver = "org.h2.Driver";
    }

    public String createURL(String host, int port, String database) {
        String url = "";
        String mode = this.extraProperties.getOrDefault("Mode", "standalone");
        if (mode.equalsIgnoreCase("standalone")) {
            url = "jdbc:h2:";
        } else if (mode.equalsIgnoreCase("memory")) {
            url = "jdbc:h2:mem:";
        } else {
            if (port != 0)
                host = host + ":" + port;
            url = "jdbc:h2:tcp://" + host + "/";
        }
        url = url + database;
        return url;
    }

    public String getAdapterName() {
        return "H2";
    }


    public String[] availableExtraProperties() {
        return new String[]{"Mode"};
    }
}