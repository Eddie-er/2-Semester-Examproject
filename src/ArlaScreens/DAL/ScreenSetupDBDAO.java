package ArlaScreens.DAL;

import ArlaScreens.BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class ScreenSetupDBDAO implements IScreenSetupDBDAO {

    private DBConnector dbConnector;

    public ScreenSetupDBDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Adds a new screensetup
     * @param screenSetup
     */
    @Override
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

    /**
     * Checks if screensetup already exists from the user
     * @param user
     * @return
     */
    @Override
    public boolean checkIfScreenSetupExist(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT ScreenSetup.UserID FROM ScreenSetup WHERE ScreenSetup.UserID =?";

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
     * Edits the screensetup config
     * @param screenSetup
     */
    @Override
    public void editScreenSetup(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE ScreenSetup SET Rows =?, Columns =? WHERE ScreenSetup.UserID =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, screenSetup.getRows());
            preparedStatement.setInt(2, screenSetup.getColumns());
            preparedStatement.setInt(3, screenSetup.getUserID());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
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

    /**
     * Gets the rows for the screensetup
     * @param user
     * @return
     */
    @Override
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

    /**
     * Gets the columns for the screensetup
     * @param user
     * @return
     */
    @Override
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

    /**
     * Deletes the screensetup to the user
     * @param user
     */
    @Override
    public void deleteScreenSetup(User user) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM ScreenSetup WHERE ScreenSetup.UserID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addBarChart(BarChart barChart) {
        try (Connection connection = dbConnector.getConnection()){
            String query = "INSERT INTO BarChart (ScreenSetupID, Row, Columns, IsSelected, FilePath) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, barChart.getScreenSetupID());
            preparedStatement.setInt(2, barChart.getRow());
            preparedStatement.setInt(3, barChart.getColumn());
            preparedStatement.setBoolean(4, barChart.isSelected());
            preparedStatement.setString(5, barChart.getFilePath());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editBarChart(BarChart barChart) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE BarChart SET Row =?, Columns =?, IsSelected =?, FilePath =? WHERE BarChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, barChart.getRow());
            preparedStatement.setInt(2, barChart.getColumn());
            preparedStatement.setBoolean(3, barChart.isSelected());
            preparedStatement.setString(4, barChart.getFilePath());
            preparedStatement.setInt(5, barChart.getScreenSetupID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public BarChart getBarChart(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM BarChart WHERE BarChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BarChart  barChart = new BarChart(
                        resultSet.getInt("ScreenSetupID"),
                        resultSet.getInt("Row"),
                        resultSet.getInt("Columns"),
                        resultSet.getBoolean("IsSelected"),
                        resultSet.getString("FilePath")
                );
                return barChart;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBarChart(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM BarChart WHERE BarChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkIfBarChartExist(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT BarChart.ScreenSetupID FROM BarChart WHERE BarChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void addLineChart(LineChart lineChart) {
        try (Connection connection = dbConnector.getConnection()){
            String query = "INSERT INTO LineChart (ScreenSetupID, Row, Columns, IsSelected, FilePath) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, lineChart.getScreenSetupID());
            preparedStatement.setInt(2, lineChart.getRow());
            preparedStatement.setInt(3, lineChart.getColumn());
            preparedStatement.setBoolean(4, lineChart.isSelected());
            preparedStatement.setString(5, lineChart.getFilePath());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editLineChart(LineChart lineChart) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE LineChart SET Row =?, Columns =?, IsSelected =?, FilePath =? WHERE LineChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, lineChart.getRow());
            preparedStatement.setInt(2, lineChart.getColumn());
            preparedStatement.setBoolean(3, lineChart.isSelected());
            preparedStatement.setString(4, lineChart.getFilePath());
            preparedStatement.setInt(5, lineChart.getScreenSetupID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public LineChart getLineChart(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM LineChart WHERE LineChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LineChart lineChart = new LineChart(
                        resultSet.getInt("ScreenSetupID"),
                        resultSet.getInt("Row"),
                        resultSet.getInt("Columns"),
                        resultSet.getBoolean("IsSelected"),
                        resultSet.getString("FilePath")
                );
                return lineChart;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteLineChart(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM LineChart WHERE LineChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkIfLineChartExist(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT LineChart.ScreenSetupID FROM LineChart WHERE LineChart.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void addExcel(Excel excel) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "INSERT INTO Excel (ScreenSetupID, Row, Columns, IsSelected, FilePath) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, excel.getScreenSetupID());
            preparedStatement.setInt(2, excel.getRow());
            preparedStatement.setInt(3, excel.getColumn());
            preparedStatement.setBoolean(4, excel.isSelected());
            preparedStatement.setString(5, excel.getFilePath());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editExcel(Excel excel) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE Excel SET Row =?, Columns =?, IsSelected =?, FilePath =? WHERE Excel.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, excel.getRow());
            preparedStatement.setInt(2, excel.getColumn());
            preparedStatement.setBoolean(3, excel.isSelected());
            preparedStatement.setString(4, excel.getFilePath());
            preparedStatement.setInt(5, excel.getScreenSetupID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Excel getExcel(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM Excel WHERE Excel.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Excel excel = new Excel(
                        resultSet.getInt("ScreenSetupID"),
                        resultSet.getInt("Row"),
                        resultSet.getInt("Columns"),
                        resultSet.getBoolean("IsSelected"),
                        resultSet.getString("FilePath")
                );
                return excel;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteExcel(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM Excel WHERE Excel.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkIfExcelExist(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT Excel.ScreenSetupID FROM Excel WHERE Excel.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void addWebsite(WebSite webSite) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "INSERT INTO WebSite (ScreenSetupID, Row, Columns, IsSelected, URL) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, webSite.getScreenSetupID());
            preparedStatement.setInt(2, webSite.getRow());
            preparedStatement.setInt(3, webSite.getColumn());
            preparedStatement.setBoolean(4, webSite.isSelected());
            preparedStatement.setString(5, webSite.getUrl());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editWebSite(WebSite webSite) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "UPDATE WebSite SET Row =?, Columns =?, IsSelected =?, URL =? WHERE WebSite.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, webSite.getRow());
            preparedStatement.setInt(2, webSite.getColumn());
            preparedStatement.setBoolean(3, webSite.isSelected());
            preparedStatement.setString(4, webSite.getUrl());
            preparedStatement.setInt(5, webSite.getScreenSetupID());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public WebSite getWebSite(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT * FROM WebSite WHERE WebSite.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WebSite webSite = new WebSite(
                        resultSet.getInt("ScreenSetupID"),
                        resultSet.getInt("Row"),
                        resultSet.getInt("Columns"),
                        resultSet.getBoolean("IsSelected"),
                        resultSet.getString("URL")
                );
                return webSite;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteWebSite(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "DELETE FROM WebSite WHERE WebSite.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkIfWebSiteExist(ScreenSetup screenSetup) {
        try (Connection connection = dbConnector.getConnection()) {
            String query = "SELECT WebSite.ScreenSetupID FROM WebSite WHERE WebSite.ScreenSetupID =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, screenSetup.getScreenSetupID());

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
