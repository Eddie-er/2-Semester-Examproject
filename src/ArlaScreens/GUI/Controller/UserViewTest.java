package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import ArlaScreens.GUI.Model.ScreenViewModel;
import com.gembox.spreadsheet.ExcelColumnCollection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserViewTest implements Initializable {

    public GridPane gridPane;

    private ScreenSetupModel screenSetupModel;
    private ScreenViewModel screenViewModel;
    private LoginModel loginModel;
    private ExcelReader excelReader;

    private TableView tblExcel;

    public UserViewTest() {
        screenSetupModel = new ScreenSetupModel();
        screenViewModel = new ScreenViewModel();
        excelReader = new ExcelReader();
        tblExcel = new TableView();
        loginModel = LoginModel.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ScreenView screenView = screenViewModel.getScreenView(loginModel.getLoggedInUser());

        if (screenView.isExcel()) {
            try {
                fillTable(excelReader.loadExcel());
                gridPane.add(tblExcel, 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable(String[][] dataSource) {
        tblExcel.getColumns().clear();

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource) {
            data.add(FXCollections.observableArrayList(row));
        }
        tblExcel.setItems(data);

        for (int i = 0; i < dataSource[0].length; i++) {
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(ExcelColumnCollection.columnIndexToName(currentColumn));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            column.setEditable(true);
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    });
            tblExcel.getColumns().add(column);
        }
    }
}

