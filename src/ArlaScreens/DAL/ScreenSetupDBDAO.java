package ArlaScreens.DAL;

import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class ScreenSetupDBDAO {

    private DBConnector dbConnector;

    public ScreenSetupDBDAO() {
        dbConnector = new DBConnector();
    }

    public void addScreenSetup(ScreenSetup screenSetup) {
        String query = "INSERT INTO ScreenSetup (UserID, Rows, Columns) VALUES (?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, screenSetup.getUserID());
            preparedStatement.setInt(2, screenSetup.getRows());
            preparedStatement.setInt(3, screenSetup.getColumns());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ScreenSetup getScreenSetup(User user) {
        try (Connection connection = dbConnector.getConnection()) {

            int userID = user.getUserID();
            String query = "SELECT * FROM ScreenSetup JOIN dbo.[User] ON dbo.[User].UserID = ScreenSetup.UserID WHERE dbo.[User].UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ScreenSetup screenSetup = new ScreenSetup(
                        resultSet.getInt("ScreenSetupID"),
                        resultSet.getInt("UserID"),
                        resultSet.getInt("Rows"),
                        resultSet.getInt("Columns")
                );
                return screenSetup;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int getRows(User user) {
        try (Connection connection = dbConnector.getConnection()){

            int userId = user.getUserID();
            String query ="SELECT ScreenSetup.Rows FROM ScreenSetup JOIN dbo.[User] ON dbo.[User].UserID = ScreenSetup.UserID WHERE dbo.[User].UserID = " + userId;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int rows = resultSet.getInt("Rows");
                return rows;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int getColumns(User user) {
        try (Connection connection = dbConnector.getConnection()) {
                int userID = user.getUserID();
                String query = "SELECT ScreenSetup.Columns FROM ScreenSetup JOIN dbo.[User] ON dbo.[User].UserID = ScreenSetup.UserID WHERE dbo.[User].UserID = " + userID;

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int columns = resultSet.getInt("Columns");
                    return columns;
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
