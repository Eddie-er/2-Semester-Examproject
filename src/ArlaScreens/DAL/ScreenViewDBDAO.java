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

    public boolean checkWebsite(int userID) {
        try (Connection connection = dbConnector.getConnection()){
            String query = "SELECT WebSite FROM ScreenView JOIN dbo.[User] ON dbo.[User].UserID = ScreenView.UserID WHERE ScreenView.UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean checkPDF(int userID) {
        try (Connection connection = dbConnector.getConnection()){
            String query = "SELECT PFDPath FROM ScreenView JOIN dbo.[User] ON dbo.[User].UserID = ScreenView.UserID WHERE ScreenView.UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean checkCSV(int userID) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT CSVPath FROM ScreenView JOIN dbo.[User] ON dbo.[User].UserID = ScreenView.UserID WHERE ScreenView.UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean checkExcel(int userID) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT ExcelPath FROM ScreenView JOIN dbo.[User] ON dbo.[User].UserID = ScreenView.UserID WHERE ScreenView.UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
