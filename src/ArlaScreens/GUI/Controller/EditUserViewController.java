package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.User;
import ArlaScreens.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditUserViewController implements Initializable {


    public TextField codeWord;
    private AdminViewController adminViewController;
    private User user;

    private UserModel userModel;

    public static boolean resetPassword;
    public static boolean isAdmin;


    @FXML
    private Button saveButton;
    @FXML
    private CheckBox adminCheck;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField userName;


    public EditUserViewController() {
        userModel = new UserModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //throws interactive Warning to double check if the codeword should be reset.


    /**
     * Checks the Check box if the chosen user is an admin.
     * @param selectedUser
     */
    public void initData(User selectedUser) {
        user = selectedUser;
        userName.setText(selectedUser.getUsername());
        if (user.isAdmin()) {
            adminCheck.setSelected(true);
            isAdmin = true;
        }
    }

    /**
     * Checks if the chosen user is an admin.
     * @param actionEvent
     */
    public void adminAction(ActionEvent actionEvent) {
        if(adminCheck.isSelected()){
            isAdmin = true;
        }
        else{
            isAdmin = false;
        }
    }

    /**
     * Closes the Window
     * @param actionEvent
     * @throws IOException
     */
    public void CancelAction(javafx.event.ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Saves any changes made to a user
     * @param actionEvent
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    public void saveAction(ActionEvent actionEvent) throws NoSuchAlgorithmException, SQLException {
        resetPassword = true;
        String username = userName.getText();
        String password;
        int userID = user.getUserID();
        boolean admin = isAdmin;


        if(resetPassword && !codeWord.getText().isEmpty()){
            password = codeWord.getText();
            userModel.editUser(username, password, admin, userID);
        }
        
        userModel.editUserName(username, userID);
        userModel.editAdmin(isAdmin,userID);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

}
