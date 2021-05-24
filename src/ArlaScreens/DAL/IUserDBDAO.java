package ArlaScreens.DAL;

import ArlaScreens.BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface IUserDBDAO {
    List<User> getAllUsers() throws SQLException;

    boolean checkIfUserExist(String userName);

    void addUser(User user);

    void editUser(User user);

    void editAdmin(int userID, boolean isAdmin) throws SQLException;

    void deleteUser(User user);

    User getUserByName(String userName) throws SQLServerException;

    User getUserByID(int userID);
}
