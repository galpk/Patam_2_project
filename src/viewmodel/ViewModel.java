package viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {
    public IntegerProperty throttle;
    public IntegerProperty timeStep;
    Model model;
    private HashMap<String, DoubleProperty> displayVariables;
    public final Runnable play,pause,stop,fastrewind,fastforward,next,back;

    public ViewModel(){
        timeStep = new SimpleIntegerProperty(0);
        Model m = new Model(timeStep);
        throttle=new SimpleIntegerProperty(0);
        displayVariables = new HashMap<String, DoubleProperty>();

        //read from file minute 50 in last class

        displayVariables.put("alt",new SimpleDoubleProperty());

        timeStep.addListener((ob,old,nw)->{

            displayVariables.get("alt").set(nw.doubleValue());
        });
        play=()->m.play();
        pause=()->m.pause();
        stop=()->m.stop();
        fastrewind=()->m.FastRewind();
        fastforward=()->m.FastForward();
        next=()->m.next();
        back=()->m.back();
    }

    public DoubleProperty getProperty(String name){
        return displayVariables.get(name);
    }



    @Override
    public void update(Observable o, Object arg) {
        this.throttle.setValue(model.getThrottle());
    }


    public String[] getHeaders(String args ){
        String[] headers = new String[0];
        String path = args;
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line =br.readLine())!= null){
                headers = line.split(",");
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }
}
