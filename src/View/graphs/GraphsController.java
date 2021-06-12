package View.graphs;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;


public class GraphsController implements Initializable {

    @FXML LineChart graphleft;
    @FXML LineChart graphright;
    @FXML LineChart graphbottom;


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
        Algorithm.setName("Anomaly algo");
        RegFlight.setName("Regular Flight");
        AnomalyFlight.setName("Anomaly Flight ");
        Anomalies.setName("Anomaly Point");
        graphbottom.setAnimated(false);
        graphbottom.setCreateSymbols(true);
        graphbottom.getData().addAll(Algorithm,RegFlight,AnomalyFlight,Anomalies);
    }
}
