package ArlaScreens.DAL;

import ArlaScreens.BE.FilePath;
import ArlaScreens.BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class FilePathDBDAO {

    private DBConnector dbConnector;

    public FilePathDBDAO() {
        dbConnector = new DBConnector();
    }

    public void addFilePath(FilePath filePath) {
        String query = "INSERT INTO FilePath (UserID, WebSiteURL, PDFPath, CSVPath, ExcelPath) VALUES (?,?,?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, filePath.getUserID());
            preparedStatement.setString(2, filePath.getWebSiteURL());
            preparedStatement.setString(3, filePath.getPdfPath());
            preparedStatement.setString(4, filePath.getCsvPath());
            preparedStatement.setString(5, filePath.getExcelPath());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getWebSiteURL(User user) {
        try (Connection connection = dbConnector.getConnection()) {

            int userID = user.getUserID();
            String query = "SELECT WebSiteURL FROM FilePath JOIN dbo.[User] ON dbo.[User].UserID = FilePath.UserID WHERE dbo.[User].UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String url = resultSet.getString("WebSiteURL");
                return url;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getPDFPath(User user) {
        try (Connection connection = dbConnector.getConnection()){

            int userID = user.getUserID();
            String query = "SELECT PDFPath FROM FilePath JOIN dbo.[User] ON dbo.[User].UserID = FilePath.UserID WHERE dbo.[User].UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String path = resultSet.getString("PDFPath");
                return path;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getCSVPath(User user) {
        try (Connection connection = dbConnector.getConnection()){

            int userID = user.getUserID();
            String query = "SELECT CSVPath FROM FilePath JOIN dbo.[User] ON dbo.[User].UserID = FilePath.UserID WHERE dbo.[User].UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String path = resultSet.getString("CSVPath");
                return path;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getExcelPath(User user) {
        try (Connection connection = dbConnector.getConnection()) {

            int userID = user.getUserID();
            String query = "SELECT ExcelPath FROM FilePath JOIN dbo.[User] ON dbo.[User].UserID = FilePath.UserID WHERE dbo.[User].UserID = " + userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String path = resultSet.getString("ExcelPath");
                return path;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
