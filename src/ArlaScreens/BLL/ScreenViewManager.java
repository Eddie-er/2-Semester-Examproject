package ArlaScreens.BLL;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.DAL.ScreenViewDBDAO;

public class ScreenViewManager {

    private ScreenViewDBDAO screenViewDBDAO;

    public ScreenViewManager() {
        screenViewDBDAO = new ScreenViewDBDAO();
    }

    public void addScreenView(ScreenView screenView) {
        screenViewDBDAO.addScreenView(screenView);
    }

    public boolean checkWebsite(int userID) {
        return screenViewDBDAO.checkWebsite(userID);
    }

    public boolean checkPDF(int userID) {
        return screenViewDBDAO.checkPDF(userID);
    }

    public boolean checkCSV(int userID) {
        return screenViewDBDAO.checkCSV(userID);
    }

    public boolean checkExcel(int userID) {
        return screenViewDBDAO.checkExcel(userID);
    }
}
