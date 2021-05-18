package ArlaScreens.GUI.Controller;

import ArlaScreens.BLL.Utils.ExcelReader;
import ArlaScreens.BLL.Utils.PDFDisplayer;
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
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    public WebView webView;
    public ImageView imageView;
    public TableView tblExcel;
    public LineChart<String, Number> lineChartView;

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
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            XYChart.Series seriesCocio = new XYChart.Series();
            XYChart.Series seriesLetmælk = new XYChart.Series();
            XYChart.Series seriesSødmælk = new XYChart.Series();
            XYChart.Series seriesMinimælk = new XYChart.Series();
            XYChart.Series seriesJuliansHjerneceller = new XYChart.Series();

            seriesCocio.setName("Cocio");
            seriesLetmælk.setName("Letmælk");
            seriesSødmælk.setName("Sødmælk");
            seriesMinimælk.setName("Minimælk");
            seriesJuliansHjerneceller.setName("Julians Hjerneceller");

            try (CSVReader dataReader = new CSVReader(new FileReader("Data/testcsv.csv"))) {
                String[] nextLine;
                while ((nextLine = dataReader.readNext()) != null) {
                    String year = String.valueOf(nextLine[0]);
                    int Cocio = Integer.parseInt(nextLine[1]);
                    seriesCocio.getData().add(new XYChart.Data(year, Cocio));
                    int Letmælk = Integer.parseInt(nextLine[2]);
                    seriesLetmælk.getData().add(new XYChart.Data(year, Letmælk));
                    int Sødmælk = Integer.parseInt(nextLine[3]);
                    seriesSødmælk.getData().add(new XYChart.Data(year, Sødmælk));
                    int Minimælk = Integer.parseInt(nextLine[4]);
                    seriesMinimælk.getData().add(new XYChart.Data(year, Minimælk));
                    int JuliansHjerneceller = Integer.parseInt(nextLine[5]);
                    seriesJuliansHjerneceller.getData().add(new XYChart.Data(year, JuliansHjerneceller));
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }

            lineChartView.getData().addAll(seriesCocio, seriesLetmælk, seriesSødmælk,seriesMinimælk,seriesJuliansHjerneceller);
        }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().load("https://www.arla.dk/");
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




