package ArlaScreens.GUI.Controller;

import ArlaScreens.BLL.Utils.AlertSystem;
import ArlaScreens.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {

    public TextField txtUserName;
    public TextField txtPassword;
    public CheckBox checkboxAdmin;
    @FXML
    private Button saveButton;

    private UserModel userModel;

    public NewUserController() {
        userModel = new UserModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleAddUser(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        boolean isSelected = checkboxAdmin.isSelected();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (userModel.checkIfUserExist(username)) {
            AlertSystem.alertUser("Bruger findes allerede", "Fejl opstod...", "Der findes allerede en bruger med det navn");
        } else {
            userModel.addUser(username, password, isSelected);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}
