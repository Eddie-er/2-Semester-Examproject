package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.PasswordAlert;
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
import java.util.ResourceBundle;

public class EditUserViewController implements Initializable {



    private AdminViewController adminViewController;
    private User user;
    private PasswordAlert passwordAlert;
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
    @FXML
    private Button ResetPassWord;

    public EditUserViewController() {
        userModel = new UserModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //throws interactive Warning to double check if the codeword should be reset.
    public void ResetPasswordAction(ActionEvent actionEvent) throws IOException{
        ResetPassWord.setOnAction(e -> PasswordAlert.display());
    }

    //Checks the Check box if the choosen User is an admin.
    public void initData(User selectedUser) {
        user = selectedUser;
        userName.setText(selectedUser.getUsername());
        if (user.isAdmin()) {
            adminCheck.setSelected(true);
            isAdmin = true;
        }
    }

    //checks if the choosen User should have Admin rights.
    public void adminAction(ActionEvent actionEvent) {
        if(adminCheck.isSelected()){
            isAdmin = true;
        }
        else{
            isAdmin = false;
        }
    }
    
    //Closes the Window without any changes.
    public void CancelAction(javafx.event.ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();

    }

    public void saveAction(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        //boolean isSelected = resetPassword;
        String username = userName.getText();
        String password;
        int userID = user.getUserID();
        boolean admin = isAdmin;


        if(resetPassword){
            password = "banan69";
        }else{
            password = user.getPassword();
        }

        //Tests the Values
        System.out.println("User name: " + username);
        System.out.println("Password: " + password);
        System.out.println("User ID: " + userID);
        System.out.println("Admin: " + admin);

        //Edits the choosen User
        userModel.editUser(username, password, admin, userID);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

}
