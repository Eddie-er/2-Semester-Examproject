package ArlaScreens.BLL;

import ArlaScreens.BE.*;
import ArlaScreens.DAL.ScreenSetupDBDAO;

public class ScreenSetupManager {
    private ScreenSetupDBDAO screenSetupDBDAO;

    public ScreenSetupManager() {
        screenSetupDBDAO = new ScreenSetupDBDAO();
    }

    public ScreenSetup getScreenSetup(User user) {
        return screenSetupDBDAO.getScreenSetup(user);
    }

    public void addScreenSetup(ScreenSetup screenSetup) {
        screenSetupDBDAO.addScreenSetup(screenSetup);
    }

    public void editScreenSetup(ScreenSetup screenSetup) {
        screenSetupDBDAO.editScreenSetup(screenSetup);
    }

    public boolean checkIfScreenSetupExist(User user) {
        return screenSetupDBDAO.checkIfScreenSetupExist(user);
    }

    public int getRows(User user) {
        return screenSetupDBDAO.getRows(user);
    }

    public int getColumns(User user) {
        return screenSetupDBDAO.getColumns(user);
    }

    public void deleteScreenSetup(User user) {
        screenSetupDBDAO.deleteScreenSetup(user);
    }

    public void addBarChart(BarChart barChart) {
        screenSetupDBDAO.addBarChart(barChart);
    }

    public void editBarChart(BarChart barChart) {
        screenSetupDBDAO.editBarChart(barChart);
    }

    public BarChart getBarChart(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getBarChart(screenSetup);
    }

    public boolean checkIfBarChartExist(ScreenSetup screenSetup) {
        return screenSetupDBDAO.checkIfBarChartExist(screenSetup);
    }

    public void addLineChart(LineChart lineChart) {
        screenSetupDBDAO.addLineChart(lineChart);
    }

    public void editLineChart(LineChart lineChart) {
        screenSetupDBDAO.editLineChart(lineChart);
    }

    public LineChart getLineChart(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getLineChart(screenSetup);
    }

    public boolean checkIfLineChartExist(ScreenSetup screenSetup) {
        return screenSetupDBDAO.checkIfLineChartExist(screenSetup);
    }

    public void addExcel(Excel excel) {
        screenSetupDBDAO.addExcel(excel);
    }

    public void editExcel(Excel excel) {
        screenSetupDBDAO.editExcel(excel);
    }

    public Excel getExcel(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getExcel(screenSetup);
    }

    public boolean checkIfExcelExist(ScreenSetup screenSetup) {
        return screenSetupDBDAO.checkIfExcelExist(screenSetup);
    }

    public void addWebsite(WebSite webSite) {
        screenSetupDBDAO.addWebsite(webSite);
    }

    public void editWebSite(WebSite webSite) {
        screenSetupDBDAO.editWebSite(webSite);
    }

    public WebSite getWebSite(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getWebSite(screenSetup);
    }

    public boolean checkIfWebSiteExist(ScreenSetup screenSetup) {
        return screenSetupDBDAO.checkIfWebSiteExist(screenSetup);
    }
}
