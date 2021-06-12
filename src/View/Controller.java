package View;

import View.bottom.Bottom;
import View.csvdialog.CSVDialog;
import View.graphs.Graphs;
import View.joystick.Joystick;
import View.panelclocks.PanelClocks;
import ViewModel.viewmodel;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

import java.util.Observable;


public class Controller extends Observable {
    @FXML PanelClocks PanelClocks;
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;


//    viewmodel viewmodel;
//    public void init(){
//        Joystick.sliderJoy1.bind(viewmodel.throttle);
//
//
//        XYChart.Series series1 = new XYChart.Series();
//        Graphs.graphleft.getData().add(series1);
//        Graphs.graphleft.setCreateSymbols(false);
//        XYChart.Series series2 = new XYChart.Series();
//        Graphs.graphright.getData().add(series2);
//        Graphs.graphright.setCreateSymbols(false);
//
//
//        XYChart.Series Algorithm = new XYChart.Series();
//        XYChart.Series RegFlight = new XYChart.Series();
//        XYChart.Series AnomalyFlight = new XYChart.Series();
//        XYChart.Series Anomalies = new XYChart.Series();
//        Algorithm.setName("Anomaly Algo");
//        RegFlight.setName("Regular Flight");
//        AnomalyFlight.setName("AAnomaly Flight");
//        Anomalies.setName("Anomalies Points");
//
//        Graphs.graphbottom.setAnimated(false);
//        Graphs.graphbottom.setCreateSymbols(false);
//        Graphs.graphbottom.getData().addAll(Algorithm,RegFlight,AnomalyFlight,Anomalies);
//
//    }


}
