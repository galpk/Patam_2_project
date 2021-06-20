package view.bottom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class BottomController  {

    @FXML public TextField TimeCounter;
    @FXML public Button Play;
    @FXML public Button Stop;
    @FXML public Button Pause;

    public Runnable onPlay,onPause,onStop,onFastRewind,onFastForward,onNext,onBack, onTime;


    public void play(){
        if (onPlay!=null) {
            onPlay.run();
        }
    }
    public void pause(){
        if (onPause!=null) {
            onPause.run();
        }
    }
    public void stop(){
        if (onStop!=null) {
            onStop.run();
        }
    }
    public void FastRewind(){
        if (onFastRewind!=null) {
            onFastRewind.run();
        }
    }
    public void FastForward(){
        if (onFastForward!=null) {
            onFastForward.run();
        }
    }
    public void next(){
        if (onNext!=null) {
            onNext.run();
        }
    }
    public void back(){
        if (onBack!=null) {
            onBack.run();
        }
    }
    public void Time(){
        if (onTime != null)
            onTime.run();
    }

}
