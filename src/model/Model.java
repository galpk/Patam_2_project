package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import test.TimeSeries;
import java.beans.XMLDecoder;
import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Model extends Observable {

    Timer t = null;
    IntegerProperty timeStep;
    public StringProperty newpathM;
    private TimeSeries tm;
    Settings settingM;
    Runnable r;
    float Alt,Speed,Dire,Roll,Pitch,Yaw;
    Thread tr;

    public float getAlt() {
        return Alt;
    }

    public float getSpeed() {
        return Speed;
    }

    public float getDire() {
        return Dire;
    }

    public float getRoll() {
        return Roll;
    }

    public float getPitch() {
        return Pitch;
    }

    public float getYaw() {
        return Yaw;
    }



    public Model() throws FileNotFoundException {
        settingM = new Settings();
        XMLDecoder xmlen = new XMLDecoder(new FileInputStream("newxml.xml"));
        settingM = (Settings) xmlen.readObject();
        xmlen.close();
    }

    public void setTm(String path) {
        this.tm = new TimeSeries(path);
    }

    public Model(IntegerProperty timeStep){
        this.timeStep = timeStep;
    }

    public void run(){
        tr =new Thread(()->this.play());
       tr.run();
    }
    public void play() {

        String line = "";
        Socket fg = null;

        try {
            fg = new Socket(settingM.getIp(), settingM.getPort());
            System.out.println(settingM.getIp() +" "+ settingM.port);
            PrintWriter out = new PrintWriter(fg.getOutputStream());
            for (int i = 0; i <tm.getDtable().get(0).Vlist.size(); i++) {
                for (int j = 0; j <tm.getDtable().size() ; j++) {
                    line = line + tm.getDtable().get(j).Vlist.get(i).toString() + ",";
                    Alt = tm.getDtable().get(settingM.MyMap.get("altimeter_indicated-altitude-ft")).Vlist.get(j);
                    Speed = tm.getDtable().get(settingM.MyMap.get("airspeed-indicator_indicated-speed-kt")).Vlist.get(j);
                    Dire=tm.getDtable().get(settingM.MyMap.get("indicated-heading-deg")).Vlist.get(j);
                    Roll=tm.getDtable().get(settingM.MyMap.get("attitude-indicator_indicated-roll-deg")).Vlist.get(j);
                    Pitch=tm.getDtable().get(settingM.MyMap.get("attitude-indicator_internal-pitch-deg")).Vlist.get(j);
                    Yaw=tm.getDtable().get(settingM.MyMap.get("side-slip-deg")).Vlist.get(j);
                    setChanged();
                    notifyObservers();


                }
                out.println(line);
                out.flush();
                line = "";
                Thread.sleep(settingM.getRate());
            }
            fg.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
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
        timeStep.set(timeStep.getValue()-15);

    }
    public void FastForward(){
        timeStep.set(timeStep.getValue()+15);

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

}
