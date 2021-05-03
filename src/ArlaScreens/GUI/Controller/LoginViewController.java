package ArlaScreens.GUI.Controller;

import ArlaScreens.GUI.Model.LoginModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

    private LoginModel loginModel;

    public LoginViewController() {
        loginModel = LoginModel.getInstance();
    }

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

    public void reset(javafx.event.ActionEvent actionEvent) {
        UserName.setText("");
        PassWord.setText("");
    }

    public void handleLogin(ActionEvent actionEvent) throws IOException, SQLServerException {
        if (loginModel.login(UserName.getText(), PassWord.getText()) != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/UserView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
