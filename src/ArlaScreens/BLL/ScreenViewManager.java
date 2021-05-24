package ArlaScreens.BLL;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BE.User;
import ArlaScreens.DAL.ScreenViewDBDAO;

public class ScreenViewManager {

    private ScreenViewDBDAO screenViewDBDAO;

    public ScreenViewManager() {
        screenViewDBDAO = new ScreenViewDBDAO();
    }

    public void addScreenView(ScreenView screenView) {
        screenViewDBDAO.addScreenView(screenView);
    }

    public void editScreenView(ScreenView screenView) {
        screenViewDBDAO.editScreenView(screenView);
    }

    public boolean checkIfScreenViewExist(User user) {
        return screenViewDBDAO.checkIfScreenViewExist(user);
    }

    public ScreenView getScreenView(User user) {
        return screenViewDBDAO.getScreenView(user);
    }

    public void deleteScreenView(User user) {
        screenViewDBDAO.deleteScreenView(user);
    }
}
