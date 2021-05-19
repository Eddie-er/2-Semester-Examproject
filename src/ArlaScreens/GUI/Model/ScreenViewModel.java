package ArlaScreens.GUI.Model;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BLL.ScreenViewManager;

public class ScreenViewModel {

    private ScreenViewManager screenViewManager;

    public ScreenViewModel() {
        screenViewManager = new ScreenViewManager();
    }

    public void addScreenView(ScreenView screenView) {
        screenViewManager.addScreenView(screenView);
    }

    public boolean checkWebsite(int userID) {
        return screenViewManager.checkWebsite(userID);
    }

    public boolean checkPDF(int userID) {
        return screenViewManager.checkPDF(userID);
    }

    public boolean checkCSV(int userID) {
        return screenViewManager.checkCSV(userID);
    }

    public boolean checkExcel(int userID) {
        return screenViewManager.checkExcel(userID);
    }
}
