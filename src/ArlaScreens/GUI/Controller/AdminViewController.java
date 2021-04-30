package ArlaScreens.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewController {

    @FXML
    private TableView<?> departmentTableView;

    @FXML
    private TableColumn<?, ?> departmentCol;

    @FXML
    private Label choosedDepLabel;

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

    }

    @FXML
    void editUserAction(ActionEvent event) {

    }

    @FXML
    void handleChoosePDFBtn(ActionEvent event) {

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

