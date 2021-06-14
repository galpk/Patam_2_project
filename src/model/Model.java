package model;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import test.AnomalyReport;
import test.TimeSeries;
import test.TimeSeriesAnomalyDetector;


import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Model extends Observable implements TimeSeriesAnomalyDetector {

    Timer t = null;
    IntegerProperty timeStep;
    public Model(IntegerProperty timeStep){
        this.timeStep = timeStep;
    }

    public void play(){
        if(t==null) {
            t=new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Sending row "+ timeStep.getValue());
                    timeStep.set(timeStep.get()+1);
                }
            },0,1000);
        }

    }
    public void pause(){
        if (t!= null){
            t.cancel();
            t=null;
        }


    }
    public void stop(){
        if (t != null) {
            t.cancel();
            t = null;
            timeStep.set(0);
        }
        timeStep.set(0);
    }
    public void FastRewind(){
        timeStep.set(timeStep.getValue()+15);

    }
    public void FastForward(){
        timeStep.set(timeStep.getValue()-15);

    }
    public void next(){


    }
    public void back(){


    }


    private int throttle;

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public void Read(){
        throttle=7;
        setChanged();
        notifyObservers();

    }

    @Override
    public void learnNormal(TimeSeries ts) {

    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }


}
