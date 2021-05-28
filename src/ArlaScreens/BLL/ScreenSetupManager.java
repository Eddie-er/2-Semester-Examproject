package ArlaScreens.BLL;

import ArlaScreens.BE.*;
import ArlaScreens.DAL.IScreenSetupDBDAO;
import ArlaScreens.DAL.ScreenSetupDBDAO;

public class ScreenSetupManager {
    private IScreenSetupDBDAO iScreenSetupDBDAO;

    public ScreenSetupManager() {
        iScreenSetupDBDAO = new ScreenSetupDBDAO();
    }

    public ScreenSetup getScreenSetup(User user) {
        return iScreenSetupDBDAO.getScreenSetup(user);
    }

    public void addScreenSetup(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.addScreenSetup(screenSetup);
    }

    public void editScreenSetup(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.editScreenSetup(screenSetup);
    }

    public boolean checkIfScreenSetupExist(User user) {
        return iScreenSetupDBDAO.checkIfScreenSetupExist(user);
    }

    public int getRows(User user) {
        return iScreenSetupDBDAO.getRows(user);
    }

    public int getColumns(User user) {
        return iScreenSetupDBDAO.getColumns(user);
    }

    public void deleteScreenSetup(User user) {
        iScreenSetupDBDAO.deleteScreenSetup(user);
    }

    public void addBarChart(BarChart barChart) {
        iScreenSetupDBDAO.addBarChart(barChart);
    }

    public void editBarChart(BarChart barChart) {
        iScreenSetupDBDAO.editBarChart(barChart);
    }

    public BarChart getBarChart(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.getBarChart(screenSetup);
    }

    public void deleteBarChart(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.deleteBarChart(screenSetup);
    }

    public boolean checkIfBarChartExist(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.checkIfBarChartExist(screenSetup);
    }

    public void addLineChart(LineChart lineChart) {
        iScreenSetupDBDAO.addLineChart(lineChart);
    }

    public void editLineChart(LineChart lineChart) {
        iScreenSetupDBDAO.editLineChart(lineChart);
    }

    public LineChart getLineChart(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.getLineChart(screenSetup);
    }

    public void deleteLineChart(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.deleteLineChart(screenSetup);
    }

    public boolean checkIfLineChartExist(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.checkIfLineChartExist(screenSetup);
    }

    public void addExcel(Excel excel) {
        iScreenSetupDBDAO.addExcel(excel);
    }

    public void editExcel(Excel excel) {
        iScreenSetupDBDAO.editExcel(excel);
    }

    public Excel getExcel(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.getExcel(screenSetup);
    }

    public void deleteExcel(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.deleteExcel(screenSetup);
    }

    public boolean checkIfExcelExist(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.checkIfExcelExist(screenSetup);
    }

    public void addWebsite(WebSite webSite) {
        iScreenSetupDBDAO.addWebsite(webSite);
    }

    public void editWebSite(WebSite webSite) {
        iScreenSetupDBDAO.editWebSite(webSite);
    }

    public WebSite getWebSite(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.getWebSite(screenSetup);
    }

    public void deleteWebSite(ScreenSetup screenSetup) {
        iScreenSetupDBDAO.deleteWebSite(screenSetup);
    }

    public boolean checkIfWebSiteExist(ScreenSetup screenSetup) {
        return iScreenSetupDBDAO.checkIfWebSiteExist(screenSetup);
    }
}
