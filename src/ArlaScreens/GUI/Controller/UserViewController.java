package ArlaScreens.GUI.Controller;

import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.BLL.Utils.PDFDisplayer;
import ArlaScreens.BLL.Utils.TresholdNode;
import ArlaScreens.GUI.Model.UserModel;
import com.gembox.spreadsheet.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    public WebView webView;
    public ImageView imageView;
    public TableView tblExcel;
    public WebView PDFView;

    @FXML
    private LineChart<Number, Number> lineChartView;

    //public LineChart<Number, Number> lineChartView;
    public javafx.scene.chart.NumberAxis NumberAxis;
    public javafx.scene.chart.CategoryAxis CategoryAxis;

    private UserModel userModel;
    private ExcelReader excelReader;
    private PDFDisplayer pdfDisplayer;


    public UserViewController() {
        userModel = new UserModel();
        excelReader = new ExcelReader();
        pdfDisplayer = new PDFDisplayer();
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    public void CSVIntoChart() throws FileNotFoundException {
            lineChartView.setTitle("Chart over ARLA stuff");




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
                    lineChartView.getData().add(serie);
                }

            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }



        }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().load("https://www.arla.dk/");

        try {
            CSVIntoChart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

        PDFView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("../View/ZoomedInPDFView.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("PDF Viewer");
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                }
            }
        });
        try {
            fillTable(excelReader.loadExcel());
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                            fillTable(excelReader.loadExcel());
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
     * Fills the tableview with data from an xlsx file
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
        }




