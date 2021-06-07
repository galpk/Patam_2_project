package View;

import View.bottom.Bottom;
import View.csvdialog.CSVDialog;
import View.graphs.Graphs;
import View.joystick.Joystick;
import View.panelclocks.PanelClocks;
import ViewModel.viewmodel;
import javafx.fxml.FXML;
import java.util.Observable;


public class Controller extends Observable {
    @FXML PanelClocks PanelClocks;
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;


    viewmodel viewmodel;
    public void init(){
        Joystick.sliderJoy1.bind(viewmodel.throttle);
    }


}
