package ArlaScreens.GUI.Model;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserModel {
    private UserManager userManager;

    private ObservableList<User> allUsers = FXCollections.observableArrayList();

    public UserModel() {
        userManager = new UserManager();
    }


    public ObservableList<User> getAllUsers() throws SQLException {
        allUsers = FXCollections.observableArrayList();
        allUsers.addAll(userManager.getAllUsers());
        return allUsers;
    }

    public void editUser(String username, String password, boolean isAdmin, int userID) throws NoSuchAlgorithmException{
        userManager.editUser(username, password, isAdmin, userID);
    }

    public void addUser(String username, String password, boolean isAdmin) throws NoSuchAlgorithmException {
        userManager.addUser(username, password, isAdmin);
    }

    public void deleteUser(User user) {
        userManager.deleteUser(user);
    }

}