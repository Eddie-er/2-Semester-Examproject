package ArlaScreens.GUI.Controller;

import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.GUI.Model.UserModel;
import com.gembox.spreadsheet.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    public WebView webView;

    public TableView tblExcel;

    private UserModel userModel;
    private ExcelReader excelReader;

    public UserViewController() {
        userModel = new UserModel();
        excelReader = new ExcelReader();
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().load("https://www.arla.dk/");
        try {
            loadExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadExcel() throws IOException {
        File file = new File("Data/Excel/MOCK_DATA.xlsx");

        ExcelFile workbook = ExcelFile.load(file.getAbsolutePath());
        ExcelWorksheet worksheet = workbook.getWorksheet(0);
        String[][] sourceData = new String[100][26];
        for (int row = 0; row < sourceData.length; row++) {
            for (int column = 0; column < sourceData[row].length; column++) {
                ExcelCell cell = worksheet.getCell(row, column);
                if (cell.getValueType() != CellValueType.NULL) {
                    sourceData[row][column] = cell.getValue().toString();
                }
            }
            fillTable(sourceData);
        }
    }

    private void fillTable(String[][] dataSource) {
        tblExcel.getColumns().clear();

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row: dataSource) {
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
