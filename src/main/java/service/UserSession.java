package service;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

public class UserSession {
    private static UserSession instance;
    private String userName;
    private String password;
    private String privileges;
    private static final Object lock = new Object(); // Lock object for synchronization

    private UserSession(String userName, String password, String privileges) {
        this.userName = userName;
        this.password = password;
        this.privileges = privileges;
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("USERNAME",userName);
        userPreferences.put("PASSWORD",password);
        userPreferences.put("PRIVILEGES",privileges);
    }

    //I implemented a lock object here to limit the user session to one person at a time.
    public static UserSession getInstance(String userName,String password, String privileges) {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new UserSession(userName, password, privileges);
                }
            }
        }
        return instance;
    }

    public static UserSession getInstance(String userName,String password) {
        return getInstance(userName, password, "NONE");
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPrivileges() {
        return this.privileges;
    }

    public void cleanUserSession() {
        synchronized (lock) {
            this.userName = "";
            this.password = "";
            this.privileges = "";
        }
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + this.userName + '\'' +
                ", privileges=" + this.privileges +
                '}';
    }
}