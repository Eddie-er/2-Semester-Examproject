package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.*;
import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.BLL.Utils.PDFDisplayer;
import ArlaScreens.BLL.Utils.TresholdNode;
import ArlaScreens.GUI.Model.FilePathModel;
import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import ArlaScreens.GUI.Model.ScreenViewModel;
import com.gembox.spreadsheet.ExcelColumnCollection;
import com.gembox.spreadsheet.SpreadsheetInfo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserViewTest implements Initializable {

    public GridPane gridPane;

    private ScreenSetupModel screenSetupModel;
    private ScreenViewModel screenViewModel;
    private LoginModel loginModel;
    private FilePathModel filePathModel;
    private ExcelReader excelReader;

    private TableView tblExcel;

    private WebView webView;

    private PDFDisplayer pdfDisplayer;

    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<?, ?> lineChartGraph = new LineChart<String, Number>(xAxis, yAxis);

    private final BarChart barChartGraph = new BarChart(xAxis, yAxis);


    public UserViewTest() {
        screenSetupModel = new ScreenSetupModel();
        screenViewModel = new ScreenViewModel();
        excelReader = new ExcelReader();
        tblExcel = new TableView();
        webView = new WebView();
        filePathModel = new FilePathModel();
        pdfDisplayer = new PDFDisplayer();
        loginModel = LoginModel.getInstance();
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.setGridLinesVisible(true);

        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

        final int numCols = screenSetup.getColumns();
        final int numRows = screenSetup.getRows();

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

        ArlaScreens.BE.BarChart barChart = screenSetupModel.getBarChart(screenSetup);

        if (barChart.isSelected()) {
            try {
                CSVIntoBar();
                gridPane.add(barChartGraph, barChart.getRow(), barChart.getColumn());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        ArlaScreens.BE.LineChart lineChart = screenSetupModel.getLineChart(screenSetup);

        if (lineChart.isSelected()) {
            try {
                CSVIntoChart();
                gridPane.add(lineChartGraph, lineChart.getRow(), lineChart.getColumn());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Excel excel = screenSetupModel.getExcel(screenSetup);

        if (excel.isSelected()) {
            try {
                fillTable(excelReader.loadExcel());
                gridPane.add(tblExcel, excel.getRow(), excel.getColumn());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WebSite webSite = screenSetupModel.getWebSite(screenSetup);

        if (webSite.isSelected()) {
            try {
                webView.getEngine().load(webSite.getUrl());
                gridPane.add(webView, webSite.getRow(), webSite.getColumn());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        openWebView();

        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Updates the GUI every 5 minutes
     */
    Thread thread = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(300_000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

                            Excel excel = screenSetupModel.getExcel(screenSetup);

                            if (excel.isSelected()) {
                                fillTable(excelReader.loadExcel());
                            }

                            ArlaScreens.BE.BarChart barChart = screenSetupModel.getBarChart(screenSetup);

                            if (barChart.isSelected()) {
                                CSVIntoBar();
                            }

                            ArlaScreens.BE.LineChart lineChart = screenSetupModel.getLineChart(screenSetup);

                            if (lineChart.isSelected()) {
                                CSVIntoChart();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    /**
     * Fills the tableview with data from an excel file
     * Link: https://www.gemboxsoftware.com/spreadsheet-java/examples/javafx-import-export-excel-tableview/5301
     * @param dataSource
     */
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

    public void CSVIntoBar() throws  FileNotFoundException{
        barChartGraph.getData().clear();
        barChartGraph.layout();
        yAxis.setTickUnit(1.0);
        yAxis.setLowerBound(0);

        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

        ArlaScreens.BE.BarChart barChart = screenSetupModel.getBarChart(screenSetup);

        String path = barChart.getFilePath();

        try (CSVReader dataReader = new CSVReader(new FileReader(path))) {

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
                barChartGraph.getData().add(serie);
            }
            barChartGraph.setTitle("Chart over ARLA stuff");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


    public void CSVIntoChart() throws FileNotFoundException {
        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

        ArlaScreens.BE.LineChart lineChart = screenSetupModel.getLineChart(screenSetup);

        String path = lineChart.getFilePath();
        //"Data/CSV/test2.csv"
        try (CSVReader dataReader = new CSVReader(new FileReader(path))) {

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
                lineChartGraph.getData().add(serie);
            }
            lineChartGraph.setTitle("Chart over ARLA stuff");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void openWebView() {
        webView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("../View/ZoomedInWebView.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Website");
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                }
            }
        });
    }
}

