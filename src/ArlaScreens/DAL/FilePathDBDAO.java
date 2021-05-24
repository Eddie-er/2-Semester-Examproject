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

    /**
     * Adds a new filepath configuration to the database
     * @param filePath
     */
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

    /**
     * Edits the filepaths
     * @param filePath
     */
    public void editFilePath(FilePath filePath) {
        try (Connection connection = dbConnector.getConnection()) {
            String query= "UPDATE FilePath SET WebSiteURL =?, PDFPath =?, CSVPath =?, ExcelPath =? WHERE FilePath.UserID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, filePath.getWebSiteURL());
            preparedStatement.setString(2, filePath.getPdfPath());
            preparedStatement.setString(3, filePath.getCsvPath());
            preparedStatement.setString(4, filePath.getExcelPath());
            preparedStatement.setInt(5, filePath.getUserID());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Checks if a filepath config exists with the user
     * @param user
     * @return
     */
    public boolean checkIfFilePathExist(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT UserID FROM FilePath WHERE UserID = ?";

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
     * Gets the filepaths from a user
     * @param user
     * @return filepaths
     */
    public FilePath getFilePath(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            int userID = user.getUserID();
            String query = "SELECT * FROM FilePath JOIN dbo.[User] ON dbo.[User].UserID = FilePath.UserID WHERE dbo.[User].UserID = " +userID;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                FilePath filePath = new FilePath(
                        resultSet.getInt("FilePathID"),
                        resultSet.getInt("UserID"),
                        resultSet.getString("WebSiteURL"),
                        resultSet.getString("PDFPath"),
                        resultSet.getString("CSVPath"),
                        resultSet.getString("ExcelPath")
                );
                return filePath;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteFilePath(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM FilePath WHERE FilePath.UserID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
