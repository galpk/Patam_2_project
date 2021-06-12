package View.bottom;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BottomController  {

    public static StringProperty CSVPath = new SimpleStringProperty();
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
    public Runnable onPlay,onPause,onStop;

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
    @FXML
    public void stop(){
        if (onStop!=null) {
            onStop.run();
        }
    }
    public void FastRewind(){

    }
    public void FastForward(){

    }
    public void next(){

    }
    public void back(){

    }

}
