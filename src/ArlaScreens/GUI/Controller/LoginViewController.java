package ArlaScreens.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginViewController implements Initializable{

    public javafx.scene.control.TextField UserName;
    @FXML
    private PasswordField PassWord;
    @FXML
    private javafx.scene.control.Button Login;
    @FXML
    private Button Cancel;
    @FXML
    private Button Reset;
    @FXML
    private ImageView imageView;

    //sætter Arla logo.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file = new File("Billeder/Arla-Logo.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    //Lukker fanen ned
    public void cancel(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.close();

    }

    // tømmer user, password og error felded
    public void reset(javafx.event.ActionEvent actionEvent) {
        UserName.setText("");
        PassWord.setText("");
    }
}
