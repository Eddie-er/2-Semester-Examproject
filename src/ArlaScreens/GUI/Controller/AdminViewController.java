package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.User;
import ArlaScreens.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private UserModel userModel;


    @FXML
    private TableView<User> departmentTableView;

    @FXML
    private TableColumn<User, String> departmentCol;

    @FXML
    private Label choosedDepLabel;
    private User selectedUser = null;

    public AdminViewController() {
        userModel = new UserModel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            departmentTableView.setItems(userModel.getAllUsers());
            departmentCol.setCellValueFactory(celldata -> celldata.getValue().usernameProperty());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        departmentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->{
            selectedUser = newValue;
        });
    }

    @FXML
    void addUserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/NewUserView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void barChartChoiceBox(ActionEvent event) {

    }

    @FXML
    void deleteUserAction(ActionEvent event) {
        if (selectedUser != null) {
            userModel.deleteUser(selectedUser);
            try {
                departmentTableView.setItems(userModel.getAllUsers());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    void editUserAction(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/EditUserView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    @FXML
    void handleChoosePDFBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en PDF...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\PDF"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
        File selectedFiles = fc.showOpenDialog(null);
    }

    @FXML
    void handleChooseCSVBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en CSV...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\CSV"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        File selectedFiles = fc.showOpenDialog(null);
    }

    @FXML
    void handleRegretBtn(ActionEvent event) {

    }

    @FXML
    void handleSaveBtn(ActionEvent event) {

    }

    @FXML
    void pieChartChoiceBox(ActionEvent event) {

    }

    @FXML
    void showPDFChoiceBox(ActionEvent event) {

    }

    @FXML
    void showWebsiteChoiceBox(ActionEvent event) {

    }


}

