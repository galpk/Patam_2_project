package view.graphs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class GraphsController implements Initializable {

    @FXML public LineChart graphleft;
    @FXML public LineChart graphright;
    @FXML public LineChart graphbottom;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series1 = new XYChart.Series();
        graphleft.getData().add(series1);
        graphleft.setCreateSymbols(false);
        XYChart.Series series2 = new XYChart.Series();
        graphright.getData().add(series2);
        graphright.setCreateSymbols(false);


        XYChart.Series Algorithm = new XYChart.Series();
        XYChart.Series RegFlight = new XYChart.Series();
        XYChart.Series AnomalyFlight = new XYChart.Series();
        XYChart.Series Anomalies = new XYChart.Series();
        Algorithm.setName("Anomaly  algo");
        RegFlight.setName("Regular Flight");
        AnomalyFlight.setName("Anomaly Flight ");
        Anomalies.setName("Anomaly Point");
        graphbottom.setAnimated(false);
        graphbottom.setCreateSymbols(true);
        graphbottom.getData().addAll(Algorithm,RegFlight,AnomalyFlight,Anomalies);
    }
    public void setLine(LineChart<Number, Number> lineChart, XYChart.Series lineSeries)
    {
        Platform.runLater(()
                -> {

            Set<Node> nodes = lineChart.lookupAll(".series" + 0);
            for (Node n : nodes) {
                n.setStyle("-fx-background-color: blue, white;\n"
                        + "    -fx-background-insets: 0, 2;\n"
                        + "    -fx-background-radius: 5px;\n"
                        + "    -fx-padding: 5px;");
            }

            lineSeries.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: blue;");
        });
    }
}
