package ArlaScreens.GUI.Controller;

import ArlaScreens.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {

    public TextField txtUserName;
    public TextField txtPassword;
    public CheckBox checkboxAdmin;

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

        userModel.addUser(username, password, isSelected);
    }
}
