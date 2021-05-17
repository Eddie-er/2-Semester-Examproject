package ArlaScreens.GUI.Model;

import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BE.User;
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
}
