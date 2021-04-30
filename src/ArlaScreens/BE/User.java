package ArlaScreens.BE;

public class User {
    private int userID;
    private String password;
    private byte[] salt;
    private String username;
    private int userviewID;

    public User(int userID, String password, byte[] salt, String username) {
        this.userID = userID;
        this.password = password;
        this.salt = salt;
        this.username = username;
    }

    public User(int userID, String password, byte[] salt, String username, int userviewID) {
        this.userID = userID;
        this.password = password;
        this.salt = salt;
        this.username = username;
        this.userviewID = userviewID;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getUserViewID() {
        return userviewID;
    }

    public void setUserViewID(int userviewID) {
        this.userviewID = userviewID;
    }
}
