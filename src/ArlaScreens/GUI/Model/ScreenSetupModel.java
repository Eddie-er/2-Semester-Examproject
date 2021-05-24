package ArlaScreens.GUI.Model;

import ArlaScreens.BE.*;
import ArlaScreens.BLL.ScreenSetupManager;

public class ScreenSetupModel {
    private ScreenSetupManager screenSetupManager;

    public ScreenSetupModel() {
        screenSetupManager = new ScreenSetupManager();
    }

    public ScreenSetup getScreenSetup(User user) {
        return screenSetupManager.getScreenSetup(user);
    }

    public void addScreenSetup(ScreenSetup screenSetup) {
        screenSetupManager.addScreenSetup(screenSetup);
    }

    public void editScreenSetup(ScreenSetup screenSetup) {
        screenSetupManager.editScreenSetup(screenSetup);
    }

    public boolean checkIfScreenSetupExist(User user) {
        return screenSetupManager.checkIfScreenSetupExist(user);
    }

    public int getRows(User user) {
        return screenSetupManager.getRows(user);
    }

    public int getColumns(User user) {
        return screenSetupManager.getColumns(user);
    }

    public void deleteScreenSetup(User user) {
        screenSetupManager.deleteScreenSetup(user);
    }

    public void addBarChart(BarChart barChart) {
        screenSetupManager.addBarChart(barChart);
    }

    public BarChart getBarChart(ScreenSetup screenSetup) {
        return screenSetupManager.getBarChart(screenSetup);
    }

    public void addLineChart(LineChart lineChart) {
        screenSetupManager.addLineChart(lineChart);
    }

    public LineChart getLineChart(ScreenSetup screenSetup) {
        return screenSetupManager.getLineChart(screenSetup);
    }

    public void addExcel(Excel excel) {
        screenSetupManager.addExcel(excel);
    }

    public Excel getExcel(ScreenSetup screenSetup) {
        return screenSetupManager.getExcel(screenSetup);
    }

    public void addWebsite(WebSite webSite) {
        screenSetupManager.addWebsite(webSite);
    }

    public WebSite getWebSite(ScreenSetup screenSetup) {
        return screenSetupManager.getWebSite(screenSetup);
    }
}
