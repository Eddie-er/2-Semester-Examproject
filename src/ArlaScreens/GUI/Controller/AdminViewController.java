package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.FilePath;
import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BE.ScreenView;
import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.AlertSystem;
import ArlaScreens.GUI.Model.FilePathModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import ArlaScreens.GUI.Model.ScreenViewModel;
import ArlaScreens.GUI.Model.UserModel;
import com.gembox.spreadsheet.SpreadsheetInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {


    
    public CheckBox checkboxWebsite;
    public CheckBox checkboxPDF;
    public CheckBox checkboxExcel;
    public CheckBox checkboxCSV;

    public TextField txtURL;
    public TextField txtPDFPath;
    public TextField txtCSVPath;
    public TextField txtExcelPath;

    private UserModel userModel;
    private ScreenSetupModel screenSetupModel;
    private ScreenViewModel screenViewModel;
    private FilePathModel filePathModel;

    @FXML
    Button editUser;

    public ChoiceBox choiceBoxRows;
    public ChoiceBox choiceBoxColumns;
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
        screenSetupModel = new ScreenSetupModel();
        screenViewModel = new ScreenViewModel();
        filePathModel = new FilePathModel();
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
            updateInformation();
        });

        choiceBoxRows.getItems().addAll(1, 2, 3, 4);
        choiceBoxColumns.getItems().addAll(1, 2, 3, 4);
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

    public void updateInformation() {
        if (selectedUser != null) {
            FilePath filePath = filePathModel.getFilePath(selectedUser);

            clearTextfields();
            clearCheckboxes();

            txtURL.setText(filePath.getWebSiteURL());
            txtPDFPath.setText(filePath.getPdfPath());
            txtCSVPath.setText(filePath.getCsvPath());
            txtExcelPath.setText(filePath.getExcelPath());

            ScreenView screenView = screenViewModel.getScreenView(selectedUser);

            if (screenView.isWebSite()) {
                checkboxWebsite.setSelected(true);
            }

            if (screenView.isPdf()) {
                checkboxPDF.setSelected(true);
            }

            if (screenView.isCsv()) {
                checkboxCSV.setSelected(true);
            }

            if (screenView.isExcel()) {
                checkboxExcel.setSelected(true);
            }
        }
    }

    public void clearTextfields() {
        txtURL.clear();
        txtPDFPath.clear();
        txtCSVPath.clear();
        txtExcelPath.clear();
    }

    public void clearCheckboxes() {
        checkboxWebsite.setSelected(false);
        checkboxPDF.setSelected(false);
        checkboxCSV.setSelected(false);
        checkboxExcel.setSelected(false);
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
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            txtPDFPath.setText(selectedFile.getAbsolutePath());
        }
    }
    //Opens file chooser and only allows CSV files
    @FXML
    void handleChooseCSVBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en CSV...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\CSV"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            txtCSVPath.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void handleChooseExcelBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en Excel...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\Excel"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("Excel Files", "*.xlxs");
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            txtExcelPath.setText(selectedFile.getAbsolutePath());
        }
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
        int rows = (int) choiceBoxRows.getSelectionModel().getSelectedItem();
        int columns = (int) choiceBoxColumns.getSelectionModel().getSelectedItem();

        ScreenSetup screenSetup = new ScreenSetup(0, selectedUser.getUserID(), rows, columns);
        screenSetupModel.addScreenSetup(screenSetup);

        boolean showWebsite = checkboxWebsite.isSelected();
        boolean showPDF = checkboxPDF.isSelected();
        boolean showCSV = checkboxCSV.isSelected();
        boolean showExcel = checkboxExcel.isSelected();

        if (screenViewModel.checkIfUserExist(selectedUser)) {
            ScreenView screenView = new ScreenView(0, selectedUser.getUserID(), showWebsite, showPDF, showCSV, showExcel);
            screenViewModel.editScreenView(screenView);
        } else {
            ScreenView screenView = new ScreenView(0, selectedUser.getUserID(), showWebsite, showPDF, showCSV, showExcel);
            screenViewModel.addScreenView(screenView);
        }

        String webSiteURL = txtURL.getText();
        String pdfPath = txtPDFPath.getText();
        String csvPath = txtCSVPath.getText();
        String excelPath = txtExcelPath.getText();

        if (filePathModel.checkIfFilePathExist(selectedUser)) {
            FilePath filePath = new FilePath(0, selectedUser.getUserID(), webSiteURL, pdfPath, csvPath, excelPath);
            filePathModel.editFilePath(filePath);
        } else {
            FilePath filePath = new FilePath(0, selectedUser.getUserID(), webSiteURL, pdfPath, csvPath, excelPath);
            filePathModel.addFilePath(filePath);
        }
    }
}