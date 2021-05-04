package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.PasswordAlert;
import ArlaScreens.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    private Button gemButton;
    @FXML
    private CheckBox adminCheck;
    @FXML
    private Button fortrydButton;
    @FXML
    private TextField userName;
    @FXML
    private Button nulstillKodeord;

    public EditUserViewController() {
        userModel = new UserModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //throws interactive Warning to double check if the codeword should be reset.
    public void nulstillKodeordAction(ActionEvent actionEvent) throws IOException{
        nulstillKodeord.setOnAction(e -> PasswordAlert.display());
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

    public void gemAction(ActionEvent actionEvent) throws NoSuchAlgorithmException {

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

        //Edits the Choosen USer
       userModel.editUser(username, password, admin, userID);

        Stage stage = (Stage) gemButton.getScene().getWindow();
        stage.close();
    }
    //Closes the Window without any changes.
    public void FortrydAction(javafx.event.ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) fortrydButton.getScene().getWindow();
        stage.close();

    }
}
