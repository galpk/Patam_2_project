package View;

import View.bottom.Bottom;
import View.csvdialog.CSVDialog;
import View.graphs.Graphs;
import View.joystick.Joystick;
import ViewModel.viewmodel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller extends Observable {
    @FXML Graphs Graphs;
    @FXML Joystick Joystick;
    @FXML Bottom Bottom;
    @FXML CSVDialog CSVDialog;

    viewmodel viewmodel;
    public void init(){
        Joystick.sliderJoy1.bind(viewmodel.throttle);

    }


}
