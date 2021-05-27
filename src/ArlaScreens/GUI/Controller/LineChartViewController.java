package ArlaScreens.GUI.Controller;

import ArlaScreens.BE.ScreenSetup;
import ArlaScreens.BLL.Utils.TresholdNode;
import ArlaScreens.GUI.Model.LoginModel;
import ArlaScreens.GUI.Model.ScreenSetupModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LineChartViewController implements Initializable {

    public LineChart lineChartGraph;
    public NumberAxis yAxis;
    public CategoryAxis xAxis;

    private LoginModel loginModel;
    private ScreenSetupModel screenSetupModel;

    public LineChartViewController() {
        loginModel = LoginModel.getInstance();
        screenSetupModel = new ScreenSetupModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CSVIntoChart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void CSVIntoChart() throws FileNotFoundException {
        ScreenSetup screenSetup = screenSetupModel.getScreenSetup(loginModel.getLoggedInUser());

        ArlaScreens.BE.LineChart lineChart = screenSetupModel.getLineChart(screenSetup);

        String path = lineChart.getFilePath();

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
            lineChartGraph.setTitle("ARLA");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
