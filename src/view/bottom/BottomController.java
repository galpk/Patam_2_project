package view.bottom;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.*;

public class BottomController  {

    @FXML TextField TimeCounter;

    @FXML
    public void OpenCSVFile()  {

        FileChooser fc=new FileChooser();
        fc.setTitle("Open CSV");
        fc.setInitialDirectory(new File("./resources"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files only","*.csv"));
        File chosen=fc.showOpenDialog(null);
        if(chosen!=null) {
            System.out.println(chosen.getName());
           // CSVPath.setValue("./resources"+ chosen.getName());

        }
    }
    public Runnable onPlay,onPause,onStop,onFastRewind,onFastForward,onNext,onBack;

    @FXML
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

}
