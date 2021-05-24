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

    public BarChart getBarChart(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getBarChart(screenSetup);
    }

    public void addLineChart(LineChart lineChart) {
        screenSetupDBDAO.addLineChart(lineChart);
    }

    public LineChart getLineChart(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getLineChart(screenSetup);
    }

    public void addExcel(Excel excel) {
        screenSetupDBDAO.addExcel(excel);
    }

    public Excel getExcel(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getExcel(screenSetup);
    }

    public void addWebsite(WebSite webSite) {
        screenSetupDBDAO.addWebsite(webSite);
    }

    public WebSite getWebSite(ScreenSetup screenSetup) {
        return screenSetupDBDAO.getWebSite(screenSetup);
    }
}
