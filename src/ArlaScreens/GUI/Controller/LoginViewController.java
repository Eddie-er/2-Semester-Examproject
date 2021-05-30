package ArlaScreens.GUI.Controller;

import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.UserModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class LoginViewController implements Initializable{

    public javafx.scene.control.TextField UserName;
    @FXML
    private Label errorLabel;
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
    private UserModel userModel;

    public LoginViewController() {
        loginModel = LoginModel.getInstance();
        userModel = new UserModel();
    }

    /**
     * Sets the Arla logo
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file = new File("Data\\Billeder/Arla-Logo.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    /**
     * Closes the window
     * @param actionEvent
     * @throws IOException
     */
    public void cancel(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.close();

    }

    /**
     * Empties the textfields
     * @param actionEvent
     */
    public void reset(javafx.event.ActionEvent actionEvent) {
        UserName.setText("");
        PassWord.setText("");
        errorLabel.setText("");
    }

    /**
     * Logs into the AdminView if the username and passwords are correct. If the user is not an admin, the UserView will show up.
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void handleLogin(ActionEvent actionEvent) throws IOException, SQLException {

        if (loginModel.login(UserName.getText(), PassWord.getText()) != null && loginModel.getLoggedInUser().isAdmin()) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/AdminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Admin");
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (loginModel.login(UserName.getText(), PassWord.getText()) != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/UserView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Bruger");
                stage.setScene(scene);
                stage.setResizable(true);
                stage.setMaximized(true);
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            errorLabel.setText("Brugernavn eller kodeord forkert. Prøv igen.");
        }
    }

    /**
     * Same method as "handleLogin" but you can log in using the ENTER key in the password field instead of pressing the login button
     * @param keyEvent
     * @throws SQLServerException
     */
    public void PasswordFieldKeyPressed(KeyEvent keyEvent) throws SQLServerException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (loginModel.login(UserName.getText(), PassWord.getText()) != null && loginModel.getLoggedInUser().isAdmin()) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/AdminView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Admin");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (loginModel.login(UserName.getText(), PassWord.getText()) != null) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../View/UserView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Bruger");
                    stage.setScene(scene);
                    stage.setResizable(true);
                    stage.setMaximized(true);
                    stage.showAndWait();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                errorLabel.setText("Brugernavn eller kodeord forkert. Prøv igen.");
            }
        }
    }
}
