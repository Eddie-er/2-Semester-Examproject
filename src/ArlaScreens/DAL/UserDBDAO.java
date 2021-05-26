package ArlaScreens.DAL;

import ArlaScreens.BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBDAO implements IUserDBDAO {

    private DBConnector dbConnector;

    public UserDBDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Gets all the users from the database
     * @return a list of users
     * @throws SQLException
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = dbConnector.getConnection();
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM dbo.[User]";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Password"),
                    resultSet.getBytes("Salt"),
                    resultSet.getString("UserName"),
                    resultSet.getBoolean("IsAdmin")
            );
            users.add(user);
        }
        connection.close();
        return users;
    }

    /**
     * Checks if a user with the given name already exists
     * @return true if username exists
     */
    @Override
    public boolean checkIfUserExist(String userName) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT UserName FROM dbo.[User] WHERE UserName = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Adds a new user to the database
     * @param user
     */
    @Override
    public void addUser(User user) {
        String query = "INSERT INTO dbo.[User](Password, UserName, Salt, IsAdmin) VALUES (?,?,?,?)";
        try (Connection connection = dbConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setBytes(3, user.getSalt());
            preparedStatement.setBoolean(4, user.isAdmin());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Edits a Choosen User in The DataBase.
     * @param user
     */
   @Override
   public void editUser(User user){
        try (Connection connection = dbConnector.getConnection()){
            String query = "UPDATE dbo.[User] SET Password =?, UserName =?, Salt =?, IsAdmin =? WHERE UserID =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setBytes(3, user.getSalt());
            preparedStatement.setBoolean(4, user.isAdmin());
            preparedStatement.setInt(5, user.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Edits Choosen Admin in the Database.
     * @param userID
     * @param isAdmin
     * @throws SQLException
     */
    @Override
    public void editAdmin(int userID, boolean isAdmin) throws SQLException {
       try (Connection connection = dbConnector.getConnection()) {
           String query = "UPDATE dbo.[User] SET IsAdmin =? WHERE UserID =?";
           PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setBoolean(1, isAdmin);
           preparedStatement.setInt(2, userID);
           preparedStatement.executeUpdate();
       } catch (SQLException throwables){
           throwables.printStackTrace();
       }
    }

    /**
     * Deletes a user from the database
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        String query = "DELETE FROM dbo.[User] WHERE UserID = ?";
        try (Connection connection = dbConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Gets a user by a specific username
     * @param userName
     * @return the user
     * @throws SQLServerException
     */
    @Override
    public User getUserByName(String userName) throws SQLServerException {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM dbo.[User] WHERE dbo.[User].UserName = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Password"),
                        resultSet.getBytes("Salt"),
                        resultSet.getString("UserName"),
                        resultSet.getBoolean("IsAdmin")
                );
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a user from a specific userID
     * @param userID
     * @return the user
     */
    @Override
    public User getUserByID(int userID) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM dbo.[User] WHERE dbo.[User].UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Password"),
                        resultSet.getBytes("Salt"),
                        resultSet.getString("UserName"),
                        resultSet.getBoolean("IsAdmin")
                );
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
