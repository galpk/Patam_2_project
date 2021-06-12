package View;

import View.bottom.Bottom;
import View.bottom.BottomController;
import View.csvdialog.CSVDialog;
import View.graphs.Graphs;
import View.joystick.Joystick;
import View.panelclocks.PanelClocks;
import ViewModel.viewmodel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


public class Controller extends Observable implements Initializable {
    @FXML PanelClocks PanelClocks;
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;

    public void init(){
        Bottom.bottomC.onPlay=()-> System.out.println("PLay");
        Bottom.bottomC.onPause=()-> System.out.println("Pause");
        Bottom.bottomC.onStop=()-> System.out.println("Stop");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewmodel viewModel = null;
//        BottomController.CSVPath.addListener((o,oldvalue, newvalue)->{
//            CSVDialog.listView.getItems().addAll(viewModel.getHeaders(BottomController.CSVPath.getValue()));
//        });

    }


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
