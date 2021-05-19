package ArlaScreens.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private int userID;
    private String password;
    private byte[] salt;
    private StringProperty username;
    private boolean isAdmin;

    public User(int userID, String password, byte[] salt, String username, boolean isAdmin) {
        this.userID = userID;
        this.password = password;
        this.salt = salt;
        this.username = new SimpleStringProperty(username);
        this.isAdmin = isAdmin;
    }

    public User(int userID, boolean isAdmin) {
        this.userID = userID;
        this.isAdmin = isAdmin;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
