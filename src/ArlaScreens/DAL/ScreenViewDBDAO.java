package ArlaScreens.DAL;

import ArlaScreens.BE.ScreenView;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ScreenViewDBDAO {
    private DBConnector dbConnector;

    public ScreenViewDBDAO() {
        dbConnector = new DBConnector();
    }

    public void addScreenView(ScreenView screenView) {
        String query = "INSERT INTO ScreenView (ScreenViewID, ScreenSetupID, BarChart, PieChart, WebSite, PDF, CSV, Excel) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, screenView.getScreenViewID());
            preparedStatement.setInt(2, screenView.getScreenSetupID());
            preparedStatement.setBoolean(3, screenView.isBarChart());
            preparedStatement.setBoolean(4, screenView.isPieChart());
            preparedStatement.setBoolean(5, screenView.isWebSite());
            preparedStatement.setBoolean(6, screenView.isPdf());
            preparedStatement.setBoolean(7, screenView.isCsv());
            preparedStatement.setBoolean(8, screenView.isExcel());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
