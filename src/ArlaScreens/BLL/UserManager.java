package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.DAL.UserDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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

    public void addUser(User user) {
        userDBDAO.addUser(user);
    }

    public User getUserByName(String userName) throws SQLServerException {
        return userDBDAO.getUserByName(userName);
    }

    public User getUserByID(int userID) {
        return userDBDAO.getUserByID(userID);
    }
}
