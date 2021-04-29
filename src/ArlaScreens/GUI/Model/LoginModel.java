package ArlaScreens.GUI.Model;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.LoginManager;

public class LoginModel {
    private static LoginModel instance = null;
    private LoginManager loginManager;
    private User user = null;

    private LoginModel() {
        loginManager = new LoginManager();
    }

    public static LoginModel getInstance() {
        if (instance == null) {
            instance = new LoginModel();
        }
        return instance;
    }

    public User login (String userName, String password) {
        user = login(userName, password);
        return user;
    }

    public User getLoggedInUser() {
        return user;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.user = loggedInUser;
    }
}
