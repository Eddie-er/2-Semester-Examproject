package ArlaScreens.DAL;

import ArlaScreens.BE.FilePath;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
        ;
    }
}
