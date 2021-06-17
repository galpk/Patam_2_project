package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import view.bottom.Bottom;
import view.bottom.BottomController;
import view.csvdialog.CSVDialog;
import view.graphs.Graphs;
import view.joystick.Joystick;
import view.panelclocks.PanelClocks;
import viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import static view.bottom.Bottom.TimeCounter;


public class Controller extends Observable  {
    @FXML PanelClocks PanelClocks;
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;
    ViewModel vm;

    public void init(ViewModel v){
        vm = v;
        vm.newpathVM.bind(Bottom.newpat);
        TimeCounter.textProperty().bind(vm.getProperty("alt").asString());
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                vm.play();
            }
        };
        Bottom.Play.setOnAction(event);
        PanelClocks.Altitude.bind(vm.Alt);
        PanelClocks.Direction.bind(vm.Dire);
        PanelClocks.Pitch.bind(vm.Pitch);
        PanelClocks.Yaw.bind(vm.Yaw);
        PanelClocks.Roll.bind(vm.Roll);
        PanelClocks.Speed.bind(vm.Speed);
        //bind other properties too

       /* Bottom.bottomC.onPlay= vm.play;
        Bottom.bottomC.onPause=vm.pause;
        Bottom.bottomC.onStop=vm.stop;
        Bottom.bottomC.onFastRewind=vm.fastrewind;
        Bottom.bottomC.onFastForward=vm.fastforward;
        Bottom.bottomC.onNext=vm.next;
        Bottom.bottomC.onBack=vm.back;*/

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
