package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.PasswordHashing;
import ArlaScreens.DAL.IUserDBDAO;
import ArlaScreens.DAL.UserDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
    private IUserDBDAO iUserDBDAO;

    public UserManager() {
        iUserDBDAO = new UserDBDAO();
    }

    public List<User> getAllUsers() throws SQLException {
        return iUserDBDAO.getAllUsers();
    }


   public void editUser(String username, String password, boolean isAdmin, int userID) throws NoSuchAlgorithmException {
        byte[] salt = PasswordHashing.getSalt();
        iUserDBDAO.editUser(new User(userID, PasswordHashing.hashPassword(password, salt), salt, username, isAdmin));
    }

    public void editAdmin(boolean isAdmin,int userID) throws SQLException {
        iUserDBDAO.editAdmin(userID, isAdmin);
    }

    public void editUserName(String userName, int userID) {
        iUserDBDAO.editUserName(userName, userID);
    }

    public void addUser(String username, String password, boolean isAdmin) throws NoSuchAlgorithmException {
        byte[] salt = PasswordHashing.getSalt();
        iUserDBDAO.addUser(new User(0, PasswordHashing.hashPassword(password, salt), salt, username, isAdmin));
    }

    public boolean checkIfUserExist(String userName) {
        return iUserDBDAO.checkIfUserExist(userName);
    }

    public void deleteUser(User user) {
        iUserDBDAO.deleteUser(user);
    }

    public User getUserByName(String userName) throws SQLServerException {
        return iUserDBDAO.getUserByName(userName);
    }

    public User getUserByID(int userID) {
        return iUserDBDAO.getUserByID(userID);
    }
}
