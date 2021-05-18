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
}
