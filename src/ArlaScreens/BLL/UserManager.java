package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.PasswordHashing;
import ArlaScreens.DAL.UserDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
    private UserDBDAO userDBDAO;

    public UserManager() {
        userDBDAO = new UserDBDAO();
    }

    public List<User> getAllUsers() throws SQLException {
        return userDBDAO.getAllUsers();
    }

    public void addUser(String username, String password) throws NoSuchAlgorithmException {
        byte[] salt = PasswordHashing.getSalt();
        userDBDAO.addUser(new User(0, PasswordHashing.hashPassword(password, salt), salt, username));
    }

    public User getUserByName(String userName) throws SQLServerException {
        return userDBDAO.getUserByName(userName);
    }

    public User getUserByID(int userID) {
        return userDBDAO.getUserByID(userID);
    }
}
