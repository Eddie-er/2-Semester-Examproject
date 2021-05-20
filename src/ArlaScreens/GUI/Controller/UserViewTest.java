package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.ScreenView;
import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.BLL.Utils.TresholdNode;
import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import ArlaScreens.GUI.Model.ScreenViewModel;
import com.gembox.spreadsheet.ExcelColumnCollection;
import com.gembox.spreadsheet.SpreadsheetInfo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserViewTest implements Initializable {

    public GridPane gridPane;

    private ScreenSetupModel screenSetupModel;
    private ScreenViewModel screenViewModel;
    private LoginModel loginModel;
    private ExcelReader excelReader;

    private TableView tblExcel;

    private WebView webView;

    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<?, ?> lineChart = new LineChart<String, Number>(xAxis, yAxis);

    public UserViewTest() {
        screenSetupModel = new ScreenSetupModel();
        screenViewModel = new ScreenViewModel();
        excelReader = new ExcelReader();
        tblExcel = new TableView();
        webView = new WebView();
        loginModel = LoginModel.getInstance();
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gridPane.setGridLinesVisible(true);

        final int numCols = screenSetupModel.getColumns(loginModel.getLoggedInUser());
        final int numRows = screenSetupModel.getRows(loginModel.getLoggedInUser());

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        ScreenView screenView = screenViewModel.getScreenView(loginModel.getLoggedInUser());

        if (screenView.isExcel()) {
            try {
                fillTable(excelReader.loadExcel());
                gridPane.add(tblExcel, 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (screenView.isCsv()) {
            try {
                CSVIntoChart();
                gridPane.add(lineChart, 1, 0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (screenView.isWebSite()) {
            try {
                webView.getEngine().load("https://www.arla.dk/");
                gridPane.add(webView, 0, 1);
            } catch (Exception e) {
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

    public void CSVIntoChart() throws FileNotFoundException {
        try (CSVReader dataReader = new CSVReader(new FileReader("Data/CSV/test2.csv"))) {

            String[] names = dataReader.readNext();
            ArrayList<XYChart.Series> chartSeries = new ArrayList();

            for (int i = 0; i < names.length; i++) {
                XYChart.Series chartSerie = new XYChart.Series();
                chartSerie.setName(names[i]);
                chartSeries.add(chartSerie);
            }
            String[] nextLine;
            while ((nextLine = dataReader.readNext()) != null) {

                String month = nextLine[0];

                for (int i = 0; i < chartSeries.size(); i++) {

                    XYChart.Data<String, Number> data = new XYChart.Data(month, Integer.parseInt(nextLine[i+1]));
                    data.setNode(new TresholdNode(data.getYValue()));
                    chartSeries.get(i).getData().add(data);
                }
            }
            for (XYChart.Series serie: chartSeries){
                lineChart.getData().add(serie);
            }
            lineChart.setTitle("Chart over ARLA stuff");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

