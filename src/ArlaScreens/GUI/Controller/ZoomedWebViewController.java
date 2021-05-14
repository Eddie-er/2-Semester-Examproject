package ArlaScreens.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;


public class ZoomedWebViewController implements Initializable {
    @FXML
    private WebView zoomedWebView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoomedWebView.getEngine().load("https://www.arla.dk/");
    }


}
