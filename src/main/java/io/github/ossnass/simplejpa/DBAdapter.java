package io.github.ossnass.simplejpa;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;


public abstract class DBAdapter {
    private static final HashMap<String, DBAdapter> registeredAdapters = new HashMap<>();

    static {
        registerAdapters();
    }

    protected String changeUserPassword;
    protected String acquireUserRoles;
    protected String driver;
    protected HashMap<String, String> extraProperties = new HashMap<>();

    public static void registerAdapters() {
        ServiceLoader<DBAdapter> adapters = ServiceLoader.load(DBAdapter.class);
        for (DBAdapter adapter : adapters) {
            registeredAdapters.put(adapter.getAdapterName(), adapter);
        }
    }

    public static String[] getAdapters() {
        String[] res = new String[registeredAdapters.keySet().size()];
        return (String[]) registeredAdapters.keySet().toArray((Object[]) res);
    }

    public static DBAdapter getAdapter(String adapterName) {
        return registeredAdapters.get(adapterName);
    }

    public abstract String createURL(String paramString1, int paramInt, String paramString2);

    public abstract String getAdapterName();

    public abstract String[] availableExtraProperties();

    public String getChangeUserPassword() {
        return this.changeUserPassword;
    }

    public String getAcquireUserRoles() {
        return this.acquireUserRoles;
    }

    public String getDriver() {
        return this.driver;
    }

    public Map<String, String> getExtraProperties() {
        return this.extraProperties;
    }
}