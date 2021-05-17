package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.User;
import ArlaScreens.GUI.Model.UserModel;
import com.gembox.spreadsheet.SpreadsheetInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Button editUser;


    @FXML
    private TableView<User> departmentTableView;
    @FXML
    private TableColumn<User, String> departmentCol;

    @FXML
    private Label choosedDepLabel;
    @FXML
    private Button aLogOut;
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
    void addUserAction(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/NewUserView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        //updates the TableView
        departmentTableView.setItems(userModel.getAllUsers());
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

    /**
     * Edit a Choosen User.
     * @param event
     * @throws IOException
     */
    @FXML
    void editAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/EditUserView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            EditUserViewController euvc = loader.getController();
            euvc.initData(departmentTableView.getSelectionModel().getSelectedItem());
            stage.showAndWait();

            //updates the TableView
            departmentTableView.setItems(userModel.getAllUsers());

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    //Opens file chooser and only allows PDF files
    @FXML
    void handleChoosePDFBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en PDF...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\PDF"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
        File selectedFiles = fc.showOpenDialog(null);
    }
    //Opens file chooser and only allows CSV files
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
    void handleChooseExcelBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en Excel...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\Excel"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("Excel Files", "*.xlxs");
        File selectedFiles = fc.showOpenDialog(null);
    }

    @FXML
    void handleRegretBtn(ActionEvent event) {

    }

    public void adminlogout(javafx.event.ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) aLogOut.getScene().getWindow();
        stage.close();
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