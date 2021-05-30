package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BE.WebSite;
import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {
    @FXML
    public WebView zoomedPDFView;

    @FXML
    private WebView zoomedWebView;

    private LoginModel loginModel;
    private ScreenSetupModel screenSetupModel;

    public WebViewController() {
        loginModel = LoginModel.getInstance();
        screenSetupModel = new ScreenSetupModel();
    }

    /**
     * Loads the given URL of the website
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

        WebSite webSite = screenSetupModel.getWebSite(screenSetup);

        String URL = webSite.getUrl();

        zoomedWebView.getEngine().load(URL);
    }
}
