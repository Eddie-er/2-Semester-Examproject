package ArlaScreens.GUI.Controller;

import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import ArlaScreens.GUI.Model.ScreenViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewTest implements Initializable {

    private ScreenSetupModel screenSetupModel;
    private ScreenViewModel screenViewModel;
    private LoginModel loginModel;

    private boolean website;
    private boolean pdf;
    private boolean csv;
    private boolean excel;

    public UserViewTest() {
        screenSetupModel = new ScreenSetupModel();
        screenViewModel = new ScreenViewModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int userID = loginModel.getLoggedInUser().getUserID();

    }
}
