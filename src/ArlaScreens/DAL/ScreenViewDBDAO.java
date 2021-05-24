package ArlaScreens.DAL;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class ScreenViewDBDAO {
    private DBConnector dbConnector;

    public ScreenViewDBDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Adds a new screenview configuration to the database
     * @param screenView
     */
    public void addScreenView(ScreenView screenView) {
        String query = "INSERT INTO ScreenView (UserID, WebSite, PDF, CSV, Excel) VALUES (?,?,?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, screenView.getUserID());
            preparedStatement.setBoolean(2, screenView.isWebSite());
            preparedStatement.setBoolean(3, screenView.isPdf());
            preparedStatement.setBoolean(4, screenView.isCsv());
            preparedStatement.setBoolean(5, screenView.isExcel());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Edits the screenview
     * @param screenView
     */
    public void editScreenView(ScreenView screenView) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE ScreenView SET WebSite =?, PDF =?, CSV=?, Excel =? WHERE UserID =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, screenView.isWebSite());
            preparedStatement.setBoolean(2, screenView.isPdf());
            preparedStatement.setBoolean(3, screenView.isCsv());
            preparedStatement.setBoolean(4, screenView.isExcel());
            preparedStatement.setInt(5, screenView.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Checks if a screenview already exists from the user
     * @param user
     * @return
     */
    public boolean checkIfScreenViewExist(User user) {
        try (Connection connection = dbConnector.getConnection()){
            String query = "SELECT ScreenView.UserID FROM ScreenView WHERE ScreenView.UserID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Gets the screenview for the logged in user
     * @param user
     * @return screenview
     */
    public ScreenView getScreenView(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            int userID = user.getUserID();
            String query = "SELECT * FROM ScreenView JOIN dbo.[User] ON dbo.[User].UserID = ScreenView.UserID WHERE ScreenView.UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ScreenView screenView = new ScreenView(
                        resultSet.getInt("ScreenViewID"),
                        resultSet.getInt("UserID"),
                        resultSet.getBoolean("WebSite"),
                        resultSet.getBoolean("PDF"),
                        resultSet.getBoolean("CSV"),
                        resultSet.getBoolean("Excel")
                );
                return screenView;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes screenview from the database
     * @param user
     */
    public void deleteScreenView(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM ScreenView WHERE ScreenView.UserID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
