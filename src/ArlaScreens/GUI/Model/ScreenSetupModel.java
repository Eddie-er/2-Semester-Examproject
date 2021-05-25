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

    public void editBarChart(BarChart barChart) {
        screenSetupManager.editBarChart(barChart);
    }

    public BarChart getBarChart(ScreenSetup screenSetup) {
        return screenSetupManager.getBarChart(screenSetup);
    }

    public boolean checkIfBarChartExist(ScreenSetup screenSetup) {
        return screenSetupManager.checkIfBarChartExist(screenSetup);
    }

    public void addLineChart(LineChart lineChart) {
        screenSetupManager.addLineChart(lineChart);
    }

    public void editLineChart(LineChart lineChart) {
        screenSetupManager.editLineChart(lineChart);
    }

    public LineChart getLineChart(ScreenSetup screenSetup) {
        return screenSetupManager.getLineChart(screenSetup);
    }

    public boolean checkIfLineChartExist(ScreenSetup screenSetup) {
        return screenSetupManager.checkIfLineChartExist(screenSetup);
    }

    public void addExcel(Excel excel) {
        screenSetupManager.addExcel(excel);
    }

    public void editExcel(Excel excel) {
        screenSetupManager.editExcel(excel);
    }

    public Excel getExcel(ScreenSetup screenSetup) {
        return screenSetupManager.getExcel(screenSetup);
    }

    public boolean checkIfExcelExist(ScreenSetup screenSetup) {
        return screenSetupManager.checkIfExcelExist(screenSetup);
    }

    public void addWebsite(WebSite webSite) {
        screenSetupManager.addWebsite(webSite);
    }

    public void editWebSite(WebSite webSite) {
        screenSetupManager.editWebSite(webSite);
    }

    public WebSite getWebSite(ScreenSetup screenSetup) {
        return screenSetupManager.getWebSite(screenSetup);
    }

    public boolean checkIfWebSiteExist(ScreenSetup screenSetup) {
        return screenSetupManager.checkIfWebSiteExist(screenSetup);
    }
}
