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
    private IUserDBDAO IUserDBDAO;

    public UserManager() {
        IUserDBDAO = new UserDBDAO();
    }

    public List<User> getAllUsers() throws SQLException {
        return IUserDBDAO.getAllUsers();
    }


   public void editUser(String username, String password, boolean isAdmin, int userID) throws NoSuchAlgorithmException {
        byte[] salt = PasswordHashing.getSalt();
        IUserDBDAO.editUser(new User(userID, PasswordHashing.hashPassword(password, salt), salt, username, isAdmin));
    }

    public void editAdmin(boolean isAdmin,int userID) throws SQLException {
        IUserDBDAO.editAdmin(userID, isAdmin);
    }

    public void addUser(String username, String password, boolean isAdmin) throws NoSuchAlgorithmException {
        byte[] salt = PasswordHashing.getSalt();
        IUserDBDAO.addUser(new User(0, PasswordHashing.hashPassword(password, salt), salt, username, isAdmin));
    }

    public boolean checkIfUserExist(String userName) {
        return IUserDBDAO.checkIfUserExist(userName);
    }

    public void deleteUser(User user) {
        IUserDBDAO.deleteUser(user);
    }

    public User getUserByName(String userName) throws SQLServerException {
        return IUserDBDAO.getUserByName(userName);
    }

    public User getUserByID(int userID) {
        return IUserDBDAO.getUserByID(userID);
    }
}
