package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    public CheckBox checkboxWebsite;
    public CheckBox checkboxExcel;
    public CheckBox checkboxBarChart;
    public CheckBox checkboxLineChart;

    public TextField txtURL;
    public TextField txtExcelPath;
    public TextField txtLineChartPath;
    public TextField txtBarChartPath;

    public TextField rowWebSite;
    public TextField columnWebSite;
    public TextField rowExcel;
    public TextField columnExcel;
    public TextField rowLineChart;
    public TextField columnLineChart;
    public TextField rowBarChart;
    public TextField columnBarChart;

    public ImageView imgExample;

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

        File file = new File("Data/Billeder/gridpane.png");
        Image image = new Image(file.toURI().toString());
        imgExample.setImage(image);
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
            screenSetupModel.deleteScreenSetup(selectedUser);

            try {
                departmentTableView.setItems(userModel.getAllUsers());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void updateInformation() {
        if (selectedUser != null) {

            clearTextfields();
            clearCheckboxes();

            ScreenSetup screenSetup = screenSetupModel.getScreenSetup(selectedUser);

            BarChart barChart = screenSetupModel.getBarChart(screenSetup);

            if (barChart.isSelected()) {
                txtBarChartPath.setText(barChart.getFilePath());
                checkboxBarChart.setSelected(true);
                rowBarChart.setText(String.valueOf(barChart.getRow()));
                columnBarChart.setText(String.valueOf(barChart.getColumn()));
            }

            LineChart lineChart = screenSetupModel.getLineChart(screenSetup);

            if (lineChart.isSelected()) {
                txtLineChartPath.setText(lineChart.getFilePath());
                checkboxLineChart.setSelected(true);
                rowLineChart.setText(String.valueOf(lineChart.getRow()));
                columnLineChart.setText(String.valueOf(lineChart.getColumn()));
            }

            Excel excel = screenSetupModel.getExcel(screenSetup);

            if (excel.isSelected()) {
                txtExcelPath.setText(excel.getFilePath());
                checkboxExcel.setSelected(true);
                rowExcel.setText(String.valueOf(excel.getRow()));
                columnExcel.setText(String.valueOf(excel.getColumn()));
            }

            WebSite webSite = screenSetupModel.getWebSite(screenSetup);

            if (webSite.isSelected()) {
                txtURL.setText(webSite.getUrl());
                checkboxWebsite.setSelected(true);
                rowWebSite.setText(String.valueOf(webSite.getRow()));
                columnWebSite.setText(String.valueOf(webSite.getColumn()));
            }
        }
    }

    public void clearTextfields() {
        txtURL.clear();
        txtLineChartPath.clear();
        txtBarChartPath.clear();
        txtExcelPath.clear();
    }

    public void clearCheckboxes() {
        checkboxWebsite.setSelected(false);
        checkboxBarChart.setSelected(false);
        checkboxLineChart.setSelected(false);
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

        if (screenSetupModel.checkIfScreenSetupExist(selectedUser)) {
            ScreenSetup screenSetup = new ScreenSetup(0, selectedUser.getUserID(), rows, columns);
            screenSetupModel.editScreenSetup(screenSetup);
        } else {
            ScreenSetup screenSetup = new ScreenSetup(0, selectedUser.getUserID(), rows, columns);
            screenSetupModel.addScreenSetup(screenSetup);
        }

        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(selectedUser);

        int rowBar = Integer.parseInt(rowBarChart.getText());
        int colBar = Integer.parseInt(columnBarChart.getText());
        boolean showBarChart = checkboxBarChart.isSelected();
        String barChartPath = txtBarChartPath.getText();

        BarChart barChart = new BarChart(screenSetup.getScreenSetupID(), rowBar, colBar, showBarChart, barChartPath);
        screenSetupModel.addBarChart(barChart);

        int rowLine = Integer.parseInt(rowLineChart.getText());
        int colLine = Integer.parseInt(columnLineChart.getText());
        boolean showLineChart = checkboxLineChart.isSelected();
        String lineChartPath = txtLineChartPath.getText();

        LineChart lineChart = new LineChart(screenSetup.getScreenSetupID(), rowLine, colLine, showLineChart, lineChartPath);
        screenSetupModel.addLineChart(lineChart);

        int rowEx = Integer.parseInt(rowExcel.getText());
        int colEx = Integer.parseInt(columnExcel.getText());
        boolean showExcel = checkboxExcel.isSelected();
        String excelPath = txtExcelPath.getText();

        Excel excel = new Excel(screenSetup.getScreenSetupID(), rowEx, colEx, showExcel, excelPath);
        screenSetupModel.addExcel(excel);

        boolean showWebsite = checkboxWebsite.isSelected();
        String webSiteURL = txtURL.getText();
        int rowWeb = Integer.parseInt(rowWebSite.getText());
        int colWeb = Integer.parseInt(columnWebSite.getText());

        WebSite webSite = new WebSite(screenSetup.getScreenSetupID(), rowWeb, colWeb, showWebsite, webSiteURL);
        screenSetupModel.addWebsite(webSite);
    }

    public void handleChooseBarChartBtn(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en CSV...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\CSV"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            txtBarChartPath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void handleChooseLineChartBtn(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Vælg en CSV...");
        fc.setInitialDirectory(new File("..\\2-Semester-Examproject\\Data\\CSV"));
        fc.getExtensionFilters().addAll();
        new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            txtLineChartPath.setText(selectedFile.getAbsolutePath());
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
}