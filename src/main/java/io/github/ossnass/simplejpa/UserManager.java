package io.github.ossnass.simplejpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Properties;


public class UserManager {
    public static final String URL = "javax.persistence.jdbc.url";
    public static final String DRIVER = "javax.persistence.jdbc.driver";
    public static final String USERNAME = "javax.persistence.jdbc.user";
    public static final String PASSWORD = "javax.persistence.jdbc.password";

    private static UserManager um;
    private String puName;
    private Properties props = new Properties();
    private HashSet<String> roles;
    private DBAdapter dbImplAdapter = null;
    private EntityManagerFactory emf;


    private UserManager() {
        this.roles = new HashSet<>();
    }

    public static synchronized UserManager getInstance() {
        if (um == null)
            um = new UserManager();
        return um;
    }

    public DBAdapter getDBAdapter() {
        return this.dbImplAdapter;
    }

    protected void setDBAdapter(DBAdapter dbImplAdapter) {
        if (dbImplAdapter == null)
            throw new IllegalArgumentException("DB Adapter cannot be null");
        this.dbImplAdapter = dbImplAdapter;
        setDriver(dbImplAdapter.getDriver());
    }

    public void setPersistanceUnitName(String puName) {
        if (puName == null)
            throw new IllegalArgumentException("The name of the persistence unit cannot be null");
        if (puName.trim().equals(""))
            throw new IllegalArgumentException("The name of the persistence unit cannot be empty srting");
        this.puName = puName;
    }

    protected void setDriver(String driver) {
        if (driver == null)
            throw new IllegalArgumentException("The driver of database cannot be null");
        if (driver.trim().equals(""))
            throw new IllegalArgumentException("The driver of database cannot be empty srting");
        this.props.put(DRIVER, driver);
    }

    public void setDBURL(String host, int port, String dbName) {
        String url = this.dbImplAdapter.createURL(host, port, dbName);
        this.props.put(URL, url);
    }

    public boolean ping() {
        this.props.setProperty(USERNAME, "pinger");
        this.props.setProperty(PASSWORD, "pinger");
        EntityManagerFactory pingEMF = Persistence.createEntityManagerFactory("PingPU", this.props);
        EntityManager em = pingEMF.createEntityManager();
        boolean res = false;


        try {
            Query q = em.createNativeQuery("select 1;");
            q.getFirstResult();
            res = true;
            DBCommon.setLastException(null);
        } catch (Exception e) {
            DBCommon.setLastException(e);
            res = false;
        } finally {
            em.close();
            pingEMF.close();
        }
        return res;
    }

    public final Properties getConnectionProperties() {
        return this.props;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (this.emf == null)
            this.emf = Persistence.createEntityManagerFactory(this.puName, this.props);
        return this.emf;
    }

    public void logOut() {
        if (this.emf != null) {
            this.emf.close();
            this.emf = null;
        }
    }

    public boolean logIn(String username, String password) {
        this.props.setProperty(USERNAME, username);
        this.props.setProperty(PASSWORD, password);
        EntityManager em = getEntityManagerFactory().createEntityManager();
        boolean res = false;
        try {
            Query q = em.createNativeQuery(this.dbImplAdapter.getAcquireUserRoles());
            this.roles.addAll(q.getResultList());
            DBCommon.setLastException(null);
            res = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
        } finally {
            em.close();
        }
        return res;
    }

    public boolean supportsRole(String role) {
        return this.roles.contains(role);
    }

    public String[] getUserRoles() {
        String[] res = new String[this.roles.size()];
        return (String[]) this.roles.toArray((Object[]) res);
    }

    public boolean changePassword(String oldPass, String newPass) {
        String reallyOldPass = this.props.getProperty(PASSWORD);

        boolean res = false;

        if (!reallyOldPass.equals(oldPass)) {
            DBCommon.setLastException(new IllegalArgumentException("Old password is wrong"));
            return false;
        }

        EntityManager em = this.emf.createEntityManager();
        try {
            String query = String.format(this.dbImplAdapter.getChangeUserPassword(), new Object[]{this.props
                    .getProperty(USERNAME), newPass});
            Query q = em.createNativeQuery(query);
            EntityTransaction t = em.getTransaction();
            t.begin();
            q.executeUpdate();
            t.commit();
            this.props.setProperty(PASSWORD, newPass);
            DBCommon.setLastException(null);
            res = true;
        } catch (Exception e) {
            DBCommon.setLastException(e);
        } finally {
            em.close();
        }
        return res;
    }
}