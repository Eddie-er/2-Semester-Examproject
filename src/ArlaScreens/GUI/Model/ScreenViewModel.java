package ArlaScreens.GUI.Model;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BE.User;
import ArlaScreens.BLL.ScreenViewManager;

public class ScreenViewModel {

    private ScreenViewManager screenViewManager;

    public ScreenViewModel() {
        screenViewManager = new ScreenViewManager();
    }

    public void addScreenView(ScreenView screenView) {
        screenViewManager.addScreenView(screenView);
    }

    public void editScreenView(ScreenView screenView) {
        screenViewManager.editScreenView(screenView);
    }

    public boolean checkIfUserExist(User user) {
        return screenViewManager.checkIfScreenViewExist(user);
    }

    public ScreenView getScreenView(User user) {
        return screenViewManager.getScreenView(user);
    }
}
