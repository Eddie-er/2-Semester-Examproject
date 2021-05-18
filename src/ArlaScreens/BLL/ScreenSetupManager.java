package ArlaScreens.BLL;

import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BE.User;
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

    public int getRows(User user) {
        return screenSetupDBDAO.getRows(user);
    }

    public int getColumns(User user) {
        return screenSetupDBDAO.getColumns(user);
    }
}
