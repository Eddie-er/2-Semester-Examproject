package ArlaScreens.DAL;

import ArlaScreens.BE.*;

public interface IScreenSetupDBDAO {
    void addScreenSetup(ScreenSetup screenSetup);

    boolean checkIfScreenSetupExist(User user);

    void editScreenSetup(ScreenSetup screenSetup);

    ScreenSetup getScreenSetup(User user);

    int getRows(User user);

    int getColumns(User user);

    void deleteScreenSetup(User user);

    void addBarChart(BarChart barChart);

    void editBarChart(BarChart barChart);

    BarChart getBarChart(ScreenSetup screenSetup);

    void deleteBarChart(ScreenSetup screenSetup);

    boolean checkIfBarChartExist(ScreenSetup screenSetup);

    void addLineChart(LineChart lineChart);

    void editLineChart(LineChart lineChart);

    LineChart getLineChart(ScreenSetup screenSetup);

    void deleteLineChart(ScreenSetup screenSetup);

    boolean checkIfLineChartExist(ScreenSetup screenSetup);

    void addExcel(Excel excel);

    void editExcel(Excel excel);

    Excel getExcel(ScreenSetup screenSetup);

    void deleteExcel(ScreenSetup screenSetup);

    boolean checkIfExcelExist(ScreenSetup screenSetup);

    void addWebsite(WebSite webSite);

    void editWebSite(WebSite webSite);

    WebSite getWebSite(ScreenSetup screenSetup);

    void deleteWebSite(ScreenSetup screenSetup);

    boolean checkIfWebSiteExist(ScreenSetup screenSetup);
}
