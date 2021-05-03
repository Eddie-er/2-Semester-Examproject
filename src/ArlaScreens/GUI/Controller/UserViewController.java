package ArlaScreens.GUI.Controller;

import ArlaScreens.GUI.Model.UserModel;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    public WebView webView;
    private UserModel userModel;

    public UserViewController() {
        userModel = new UserModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().load("https://www.arla.dk/");
    }
}
