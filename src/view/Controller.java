package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.bottom.Bottom;
import view.csvdialog.CSVDialog;
import view.graphs.Graphs;
import view.joystick.Joystick;
import view.panelclocks.PanelClocks;
import viewmodel.ViewModel;
import javafx.fxml.FXML;
import java.util.Observable;

public class Controller extends Observable
{
    @FXML PanelClocks PanelClocks;
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;
    ViewModel vm;
   /* public void configurePlayer(){
        Bottom.Play.setOnMouseClicked(mouseEvent ->{
            vm.play();
            Bottom.BottomSlider.setDisable(true);
        });
        Bottom.Pause.setOnMouseClicked(mouseEvent ->{
            vm.pause();
            Bottom.BottomSlider.setDisable(false);
        });
        Bottom.Stop.setOnMouseClicked(mouseEvent ->{
            vm.stop();
            Bottom.BottomSlider.setDisable(false);
        });
//        Bottom.BottomSlider.valueChangingProperty().addListener((obs, oldVal, newVal) -> {
////            if (!newVal) {
////               vm.changeTimeStamp((int)  Bottom.BottomSlider.getValue());
////            }
//        });
    }*/

    public static void injectTimeStamp(int value) {
//        setTimeStamp(value);
//        setClock(value / ratio);
    }
    public void init(ViewModel v)
    {
        vm = v;
        vm.newpathVM.bind(CSVDialog.newpat);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                vm.play();
                vm.stop();
                vm.pause();
                vm.FastRewind();
                vm.FastForward();
                vm.back();
                vm.next();
                vm.PlaySpeed();


            }
        };
        Bottom.Play.setOnAction(event);
        Bottom.Stop.setOnAction(event);
        Bottom.Pause.setOnAction(event);
        Bottom.FastRewind.setOnAction(event);
        Bottom.FastForward.setOnAction(event);
        Bottom.next.setOnAction(event);
        Bottom.back.setOnAction(event);
        Bottom.PlaySpeed.setOnAction(event);

        PanelClocks.Altitude.bind(vm.Alt);
        PanelClocks.Direction.bind(vm.Dire);
        PanelClocks.Pitch.bind(vm.Pitch);
        PanelClocks.Yaw.bind(vm.Yaw);
        PanelClocks.Roll.bind(vm.Roll);
        PanelClocks.Speed.bind(vm.Speed);

        Joystick.throttle.bind(vm.throttle);
        Joystick.Rudder.bind(vm.Rudder);
        Joystick.aileron.bind(vm.aileron);
        Joystick.elevator.bind(vm.elevator);
        //bind other properties too

    }



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
